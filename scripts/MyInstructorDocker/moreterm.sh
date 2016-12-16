#!/bin/bash

# Filename: moreterm.sh
# Description:
#     This is the script to be run by the student.
#     Note: It needs 'start.config' file.
#
#     It will perform the following tasks:
#     a. Spawn another terminal for the student attached to the container

# Error code returned by docker inspect
if [ "$#" -ne 1 ]; then
    echo "./start.sh <labname>"
    exit 0
fi
lab=$1

SUCCESS=0
FAILURE=1

CWD=`pwd`
#echo "Current directory is $CWD"
CONFIG=${CWD}/start.config.$lab

if [ -f $CONFIG ]
then
    #echo "Config file $CONFIG exists, proceeding"
    # Read start.config
    . $CONFIG
else
    echo "Config file $CONFIG does not exist, exiting."
    exit 1
fi

#echo "Name of container is $CONTAINER_NAME"
#echo "Name of container image is $CONTAINER_IMAGE"

# Check to see if $CONTAINER_NAME container has been created or not
docker inspect -f {{.Created}} $CONTAINER_NAME &> /dev/null
result=$?
#echo "inspect result is $result"
if [ $result -eq $FAILURE ]
then
    echo "ERROR: Container $CONTAINER_NAME is not running!"
    exit 1
fi

# Start a terminal and a shell in the container
gnome-terminal -x docker exec -it $CONTAINER_NAME script -q -c "/bin/bash -c 'cd ; . .profile ; exec ${SHELL:-sh}'" /dev/null &
