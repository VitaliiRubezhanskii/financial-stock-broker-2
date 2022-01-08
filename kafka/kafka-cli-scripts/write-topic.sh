#!/usr/bin/env bash
echo "$(cat data.json)" | kafka-console-producer \
    --broker-list localhost:9092 \
    --topic $1