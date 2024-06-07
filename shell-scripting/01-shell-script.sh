#!/bin/bash

echo "my name is rohit"

set -x #set the file in debug mode
set -e # exit the script when there is an error
set -o pipefail # exit when there is a pipe as will



a=4
b=22

if [ $a -gt $b ]
then
    echo "a is greater than b"
else
    echo "b is greater than a"
fi