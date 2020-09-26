#!/bin/bash
#
# Create a master Labtainer image for use in running Labtainers from a container
# on any system that has Docker installed, withou having to install Labtainers.
# Thanks for Olivier Berger for this contribution.
#
here=`pwd`
cd ../
mkdir -p workspace_master
rm -fr workspace_master/*
cp -a $LABTAINER_DIR/headless-lite/Dockerfile.labtainer.master.headless workspace_master/Dockerfile.labtainer.master.headless
cp -aR workspace/system workspace_master/
cp -a $LABTAINER_DIR/headless-lite/motd workspace_master
cp -a $LABTAINER_DIR/headless-lite/docker-entrypoint workspace_master
cp -a  $LABTAINER_DIR/headless-lite/wait-for-it.sh workspace_master
cp -a  $LABTAINER_DIR/headless-lite/doterm.sh workspace_master
cp -a  $LABTAINER_DIR/headless-lite/doupdate.sh workspace_master
cd workspace_master
cp -a $LABTAINER_DIR/distrib/labtainer.tar ./

cat <<EOT >bashrc.labtainer.master
   if [[ ":\$PATH:" != *":./bin:"* ]]; then 
       export PATH="\${PATH}:./bin:/home/labtainer/labtainer/trunk/scripts/designer/bin"
       export LABTAINER_DIR=/home/labtainer/labtainer/trunk
   fi
EOT

CACHE="--no-cache"
if [[ "$1" == -c ]]; then
    CACHE=""
fi
docker build $CACHE --build-arg DOCKER_GROUP_ID="$(getent group docker | cut -d: -f3)" -f Dockerfile.labtainer.master.headless -t labtainer.master.headless:latest .
docker tag labtainer.master.headless labtainers/labtainer.master.headless
cd $here
