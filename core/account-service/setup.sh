#!/usr/bin/env bash

java -jar account-service-0.0.1-SNAPSHOT.jar

port="8091"
host="http://account"
echo "Running with args " host port
>&2 echo "!!!!!!!! Check conteiner_a for available !!!!!!!!"

until curl http://"$host":"$port"; do
  >&2 echo "Conteiner_A is unavailable - sleeping"
  sleep 1
done

>&2 echo "Conteiner_A is up - executing command"