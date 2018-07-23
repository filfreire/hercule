#!/bin/bash

# Raise docker instance with remote browser + webdriver, run tests, kill docker instance
set -x

FF_NAME=default_firefox
java -version
docker version

# Start containers
docker run -d -P --name=$FF_NAME selenium/standalone-firefox
docker ps

HOST=http://$(docker port default_firefox 4444)
echo $HOST

# Run tests
mvn clean test -P integration -DremoteHost=$HOST

# Stop containers
docker stop $(docker ps -a -q --filter "name=$FF_NAME")
docker ps
docker rm $(docker ps -a -f status=exited -q)
