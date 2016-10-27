#!/bin/bash

# capinout.sh
# Description: * Re-direct stdin and stdout to files

# Usage: capinout.sh <execprog>
# Arguments:
#     <execprog> - program to execute

EXECPROG=$1
PROGNAME=`basename $EXECPROG`
if [ $# -gt 1 ]
then
    shift
    PROGRAM_ARGUMENTS=$*
else
    PROGRAM_ARGUMENTS=""
fi
#echo "EXECPROG is ($EXECPROG)"
#echo "PROGNAME is ($PROGNAME)"
#echo "PROGRAM_ARGUMENTS is ($PROGRAM_ARGUMENTS)"
#echo "Program to execute is $EXECPROG"
#echo "basename of $EXECPROG is $PROGNAME"
timestamp=$(date +"%Y%m%d%H%M%S")
stdinfile="$HOME/.local/result/$PROGNAME.stdin.$timestamp"
stdoutfile="$HOME/.local/result/$PROGNAME.stdout.$timestamp"

echo "stdinfile is $stdinfile"
echo "stdoutfile is $stdoutfile"

# kill the tee when the pipe consumer dies
#
#set -o pipefail

# Setup trap to handle SIGINT and SIGTERM
trap "echo exiting due to signal; echo caught SIGINT >> $stdinfile " SIGINT
trap "echo exiting due to signal; echo caught SIGTERM >> $stdinfile " SIGTERM

pipe=$(mktemp -u)
if ! mkfifo $pipe; then
   echo "ERROR: pipe create failed" >%2
   exit 1
fi

exec 3<>$pipe
rm $pipe
(echo $BASHPID >&3; tee $stdinfile) | (unbuffer -p $EXECPROG $PROGRAM_ARGUMENTS; r=$?; kill $(head -n1 <&3); exit $r) | tee $stdoutfile

TEE_PID=$(ps | grep [t]ee | awk '{print $1}')
kill $TEE_PID

#exit ${PIPESTATUS[1]}

###### Call
#####tee $stdinfile | stdbuf -oL -eL $EXECPROG $PROGRAM_ARGUMENTS | tee $stdoutfile

