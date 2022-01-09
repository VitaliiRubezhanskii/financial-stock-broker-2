#!/usr/bin/env bash

#sleep 2m
java -jar account-service.jar -Djdk.tls.client.protocols=TLSv1.2
