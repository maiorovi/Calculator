#!/usr/bin/env bash

WORKDIR=$(dirname "$0")/..

cd $WORKDIR

docker container stop $(docker container ps -a -q)
docker container rm calculator-app
docker image rm calculator-app