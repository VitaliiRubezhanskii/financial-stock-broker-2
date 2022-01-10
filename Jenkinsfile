pipeline {
    environment {
        accountServiceImageName = "vitalii1992/account-service"
        analyticsServiceImageName = "vitalii1992/analytics-service"
        orderServiceImageName = "vitalii1992/order-service"
        quotesProviderServiceImageName = "vitalii1992/quotes-provider-service"
        feignServiceImageName = "vitalii1992/feign-service"

        registryCredential = 'DockerHub'

        ANALYTICS_SERVICE_IMAGE = 'vitalii1992/analytics-service:latest'
        ORDER_SERVICE_IMAGE = 'vitalii1992/order-service:latest'
        ACCOUNT_SERVICE_IMAGE = 'vitalii1992/account-service:latest'
        QUOTES_PROVIDER_SERVICE_IMAGE = 'vitalii1992/quotes-provider-service:latest'
        FEIGN = 'vitalii1992/feign-service:latest'
     }
    agent { label 'master'}
    stages {
        stage('Checkout code') {
              steps{
                cleanWs()
                git branch: 'master',
                credentialsId: 'GitHub',
                url: "https://github.com/VitaliiRubezhanskii/financial-stock-broker-2.git"
            }
        }

        stage('Build'){
            steps{
                script {
                   sh './gradlew clean build'
                }
            }
        }

        stage('Docker image'){
            steps {
                script {
                    docker.withRegistry('', registryCredential){

                    def accountServiceImage = docker.build(accountServiceImageName + ":latest", './account-service')
                    def analyticsServiceImage = docker.build(analyticsServiceImageName + ":latest", './analytics-service')
                    def quotesProviderServiceImage = docker.build(quotesProviderServiceImageName + ':latest', './quotes-provider-service')
                    def orderServiceImage = docker.build(orderServiceImageName + ":latest", './order-service')
                    def feignServiceImage = docker.build(feignServiceImageName + ":latest", './feign-hystrix-client')

                    accountServiceImage.push()
                    analyticsServiceImage.push()
                    quotesProviderServiceImage.push()
                    orderServiceImage.push()
                    feignServiceImage.push()

                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                 script {

                   // Deploy KeyCloak
                  sh 'kubectl create -f ./keycloak/keycloak-storage.yml'
                  sh 'kubectl create -f ./keycloak/keycloak-deployment.yml'
                  sh (""" wget -q -O - https://raw.githubusercontent.com/keycloak/keycloak-quickstarts/latest/kubernetes-examples/keycloak-ingress.yaml | \
                      sed "s/KEYCLOAK_HOST/keycloak.$(minikube ip).nip.io/" | \
                      kubectl create -f - """)
                  sh (""" KEYCLOAK_URL=https://keycloak.$(minikube ip).nip.io/auth &&
                      echo "" &&
                      echo "Keycloak:                 $KEYCLOAK_URL" &&
                      echo "Keycloak Admin Console:   $KEYCLOAK_URL/admin" &&
                      echo "Keycloak Account Console: $KEYCLOAK_URL/realms/myrealm/account" """)
                  sh (""" KEYCLOAK_URL=http://$(minikube ip):$(kubectl get services/keycloak -o go-template='{{(index .spec.ports 0).nodePort}}')/auth &&
                      echo "" &&
                      echo "Keycloak:                 $KEYCLOAK_URL" &&
                      echo "Keycloak Admin Console:   $KEYCLOAK_URL/admin" &&
                      echo "Keycloak Account Console: $KEYCLOAK_URL/realms/myrealm/account" """)

                   // Deploy MongoDB
                  sh 'kubectl apply -f ./mongodb/mongodb-secret.yml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./mongodb/mongodb-deployment.yml --kubeconfig=../../kubeconfig/config'

                  // Deploy postgres
                  sh 'kubectl apply -f ./postgres/postgres-configmap.yml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./postgres/postgres-storage.yml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./postgres/postgres-service.yml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./postgres/postgres-deployment.yml --kubeconfig=../../kubeconfig/config'

                    // Deploy microservices configs
                  sh 'kubectl apply -f ./configuration/kubernetes/account/account-configmap.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./configuration/kubernetes/analytics/analytics-configmap.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./configuration/kubernetes/order/order-configmap.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./configuration/kubernetes/quotes-provider/finprovider-configmap.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./configuration/kubernetes/feign/feign-configmap.yaml --kubeconfig=../../kubeconfig/config'

                    // Deploy rbac & ingress
                  sh 'kubectl apply -f ./configuration/kubernetes/cluster-rbac.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./configuration/kubernetes/ingress.yaml --kubeconfig=../../kubeconfig/config'

                  sh 'sleep 7s'

                  sh 'kubectl apply -f ./account-service/deploy.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./order-service/deploy.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./analytics-service/deploy.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./quotes-provider-service/deploy.yaml --kubeconfig=../../kubeconfig/config'
                  sh 'kubectl apply -f ./feign-hystrix-client/deploy.yaml --kubeconfig=../../kubeconfig/config'

                 }
             }
        }
    }
}