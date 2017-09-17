#!/usr/bin/env bash

WORKDIR=$(dirname "$0")/..

echo $WORKDIR

cd $WORKDIR

mvn package -DskipTests

docker image build -t calculator-app .
docker container run -p 8080:8080 -d --name calculator-app calculator-app