#!/usr/bin/env bash

#sleep 3m
java -jar quotes-provider-service.jar -Djdk.tls.client.protocols=TLSv1.2
