#!/usr/bin/env bash
#https://github.com/jenkinsci/docker/blob/master/README.md
docker run -d \
  -v `pwd`/data:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -p 8080:8080 -p 50000:50000 jenkins:latest