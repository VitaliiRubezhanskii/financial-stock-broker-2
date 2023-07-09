# **Deploy kafka cluster with Strimzi**

 1. Check if Strimzi Helm chart already present in Helm repo:
    `helm repo list` .

 2. Add the Strimzi Helm chart repository if it is absent with command: 
    `helm repo add strimzi https://strimzi.io/charts/` .
 3. Install the chart with release name my-release:
    `helm install my-release strimzi/strimzi-kafka-operator -n kafka` .
 4. Make sure that service account, kafka user, kafka topic etc are deployed (
    `KafkaTopic.yaml`, `KafkaUser.yaml`, `registry-crd.yaml`, `serviceAccounts.yaml`)
    if not deployed so deploy them `kubectl apply -f <file-name.yaml> -n kafka`.
 5. Deploy Kafka Cluster. In `strimzi` folder find `strimzi-kafka.yml` and deploy 
    `kubectl apply -f strimzi-kafka.yml -n kafka` .
 6. Deploy Kafka UI:  `kubectl apply -f kafka-ui.yml -n kafka`

# Deploy Schema Registry with Strimzi
 1. Go to strimzi/charts/strimzi-registry-operator and run `helm install kafka-schema-registry  . -n kafka` 

 That's it!
 You Can deploy Kubernetes Dashboard: `kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml`
 And access it on `http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/.`

 To get access token run `kubectl -n kube-system get secret` and than `kubectl -n kube-system describe secret deployment-controller-token-****`
