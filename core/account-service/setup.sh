#!/usr/bin/env bash

java -jar account-service-0.0.1-SNAPSHOT.jar -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=:5005
