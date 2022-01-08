#!/bin/bash

function build() {
    docker build -f ./jenkins/Dockerfile -t "$1":latest .
}

function run() {
  docker-compose --file docker-compose.dev.yml up -d
#    docker run --name "$1" -p 8080:8080 -p 50000:50000 -v jenkins:/var/jenkins_home "$1":latest
}

function imageLatestExistsByName() {
  echo "Y" | docker system prune
  if [ ! "$(docker ps -a | grep "$1" | awk '{ print $2 }')" ]; then
    run
  fi;
}

imageLatestExistsByName vitalii1992/jenkins-fsb:latest;
