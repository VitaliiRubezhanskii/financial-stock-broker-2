#!/bin/bash

cat employee.json | kafka-console-producer --broker-list broker:9092 --topic $1
