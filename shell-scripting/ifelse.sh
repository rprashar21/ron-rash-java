#!/bin/bash
####################################
# AUTHOR : Rohit
# DATE :
# This script check the healthh of ur node "
# Version:v1
######################################

set -x #set the file in debug mode
set -e # exit the script when there is an error
set -o pipefail # exit when there is a pipe as will

df -h

free -g

nproc

a=4
b=22

if [ $a -gt $b ]
then
    echo "a is greater than b"
else
    echo "b is greater than a"
fi

for i in {1.5}; do echo $1; done

