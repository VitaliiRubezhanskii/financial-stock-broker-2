#!/usr/bin/env bash

kafka-console-consumer \
    --bootstrap-server localhost:19092 \
    --topic quote \
    --property schema.registry.url=http://localhost:8081 \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer