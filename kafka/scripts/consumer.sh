#!/usr/bin/env bash

kafka-console-consumer --topic $1 --bootstrap-server broker:9092 --from-beginning