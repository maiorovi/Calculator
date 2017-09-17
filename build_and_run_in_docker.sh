#!/usr/bin/env bash

mvn package

docker image build -t calculator-app .
docker container run -p 8080:8080 -d --name calculator-app calculator-app