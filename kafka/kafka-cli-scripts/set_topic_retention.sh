#!/usr/bin/env bash
kafka-topics --zookeeper zookeeper:2181 --alter --topic $1 --config retention.ms=1000