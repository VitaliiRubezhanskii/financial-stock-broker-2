#!/usr/bin/env bash

curl \
    --header "Content-Type: application/vnd.schemaregistry.v1+json" \
    --request POST \
    --data '{"schema": "{\r\n    \"namespace\": \"avro\",\r\n    \"type\": \"record\",\r\n    \"name\": \"Quote\",\r\n    \"fields\": [\r\n      {\r\n        \"name\": \"id\",\r\n        \"type\": \"string\"\r\n      },\r\n      {\r\n        \"name\": \"date\",\r\n        \"type\": \"string\"\r\n      },\r\n      {\r\n        \"name\": \"ticket\",\r\n        \"type\": \"string\"\r\n      },\r\n      {\r\n        \"name\": \"open\",\r\n        \"type\": \"string\"\r\n      },\r\n      {\r\n        \"name\": \"high\",\r\n        \"type\": \"string\"\r\n      },\r\n      {\r\n        \"name\": \"close\",\r\n        \"type\": \"string\"\r\n      },\r\n      {\r\n        \"name\": \"volume\",\r\n        \"type\": \"string\"\r\n      },\r\n      {\r\n        \"name\": \"low\",\r\n        \"type\": \"string\"\r\n      }\r\n    ]\r\n  }"}' \
    http://localhost:8081/subjects/quote-value/versions
