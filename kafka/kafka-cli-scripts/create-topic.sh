#!/usr/bin/env bash

kafka-topics --create \
  --zookeeper localhost:22181 \
  --replication-factor 3 --partitions 3 \
  --topic analytics-KSTREAM-AGGREGATE-STATE-STORE-0000000002-repartition