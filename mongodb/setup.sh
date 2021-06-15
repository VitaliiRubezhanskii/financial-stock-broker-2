#!/usr/bin/env bash
#if [ -f /replicated.txt ]; then
#  echo "Mongo is already set up"
#else
#  echo "Setting up mongo replication and seeding initial data..."
#  # Wait for few seconds until the mongo server is up
#  sleep 10
#  mongo mongo1:27017 replicate.js
#  echo "Replication done..."
#  # Wait for few seconds until replication takes effect
#  sleep 15
#  mongoimport --host mongo1 --db auth --collection user --type json --file /user.json --jsonArray
#  mongoimport --host mongo1 --db auth --collection authClientDetails --type json --file /authClientDetails.json --jsonArray
#  mongoimport --host mongo1 --db auth --collection mongoAccessToken --type json --file /mongoAccessToken.json --jsonArray
#  mongoimport --host mongo1 --db auth --collection mongoRefreshToken --type json --file /mongoRefreshToken.json --jsonArray

#  echo "Seeding done..."
#  touch /replicated.txt
#fi

  sleep 15
  mongo --host mongocustom
  mongoimport --db auth --collection user --type json --file /user.json --jsonArray
  mongoimport --host mongodb-service.default.svc.cluster.local --db auth --collection authClientDetails --type json --file /authClientDetails.json --jsonArray
  mongoimport --host mongodb-service.default.svc.cluster.local --db auth --collection mongoAccessToken --type json --file /mongoAccessToken.json --jsonArray
  mongoimport --host mongodb-service.default.svc.cluster.local --db auth --collection mongoRefreshToken --type json --file /mongoRefreshToken.json --jsonArray
echo "Seeding done..."

