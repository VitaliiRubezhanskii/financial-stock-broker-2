#  Useful Kubernets commands for deploying microservices
1. Get url for <service_name> of service available on local machine `minikube service <service_name> -n kafka --url`
2. SSH into minikube `minikube ssh` (empty space `docker system prune`)
3. `minikube tunnel` runs as a process, creating a network route on the host to the service CIDR of the cluster using the cluster’s IP address as a gateway.