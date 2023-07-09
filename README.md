# MyPetProjects
My java learning projects 

Docker compose run services order:

1. Run **Mongo-replicator** which brings up mongo cluster
2. Run **Rabbitmq**
3. Run **Zookeeper**
4. Run **Broker**
5. Run **Schema-registry**
6. Run **Cassandra**
7. Run **Kafka-connect-cp**
8. Run **Connectui**
9. Run **ElasticSearch**
10. Run **Kibana**
11. Run **Logstash**

kubectl config use-context minikube
minikube start --driver=hyperkit --cpus=6 --memory 8000MB

# **Deploy Kafka on Minikube**
# 1. First add the Strimzi Chart Repository
helm repo add strimzi https://strimzi.io/charts/
# 2. Install version of Strimzi Cluster Operator
helm install strimzi/strimzi-kafka-operator --name strimzi-release --namespace kafka --version 0.28.0
