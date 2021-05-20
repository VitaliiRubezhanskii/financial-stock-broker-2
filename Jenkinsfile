pipeline {
 environment {
    accountServiceImageName = "vitalii1992/account-service"
    analyticsServiceImageName = "vitalii1992/analytics-service"
    orderServiceImageName = "vitalii1992/order-service"
    quotesProviderServiceImageName = "vitalii1992/quote-provider-service"

    authServiceImageName = "vitalii1992/auth-service"
    feignServiceImageName = "vitalii1992/feign-service"
    gatewayServiceImageName = "vitalii1992/api-gateway-service"
    tracingServiceImageName = "vitalii1992/tracing-service"
    turbineServiceImageName = "vitalii1992/turbine-service"

    registryCredential = 'DockerHub'

    ANALYTICS_SERVICE_IMAGE = 'vitalii1992/analytics-service:latest'
    ORDER_SERVICE_IMAGE = 'vitalii1992/order-service:latest'
    ACCOUNT_SERVICE_IMAGE = 'vitalii1992/account-service:latest'
    QUOTES_PROVIDER_SERVICE_IMAGE = 'vitalii1992/quote-provider-service:latest'

    AUTH_SERVICE_IMAGE = 'vitalii1992/auth-service:latest'
    FEIGN_CLIENT_SERVICE_IMAGE = 'vitalii1992/feign-service:latest'
    API_GATEWAY_SERVICE_IMAGE = 'vitalii1992/api-gateway-service:latest'
    TRACING_SERVICE_IMAGE = 'vitalii1992/tracing-service:latest'
}
    agent { label 'master'}
    stages {

    stage('Checkout code') {
          steps{
            cleanWs()
            git branch: 'spring-cloud-k8s-native',
            credentialsId: 'GitHub',
            url: "https://github.com/VitaliiRubezhanskii/financial-stock-broker-2.git"
        }
    }

//     stage('Build'){
//         steps{
//             script {
//                sh 'mvn clean install -DskipTests=true'
//             }
//         }
//     }
//
//     stage('Docker image'){
//         steps {
//             script {
//                 docker.withRegistry('', registryCredential){
//
//                 def accountServiceImage = docker.build(accountServiceImageName + ":latest", './core/account-service')
//                 def analyticsServiceImage = docker.build(analyticsServiceImageName + ":latest", './core/analytics-service')
//                 def orderServiceImage = docker.build(orderServiceImageName + ":latest", './core/order-service')
//                 def quotesProviderServiceImage = docker.build(quotesProviderServiceImageName + ':latest', './core/quotes-provider-service')
//
//                 def authServiceImage = docker.build(authServiceImageName + ':latest', './support/auth')
//                 def feignServiceImage = docker.build(feignServiceImageName + ':latest', './support/feign-hystrix-client')
//                 def gatewayServiceImage = docker.build(gatewayServiceImageName + ':latest', './support/gateway')
//                 def tracingServiceImage = docker.build(tracingServiceImageName + ':latest', './support/tracing')
//                 def turbineServiceImage = docker.build(tracingServiceImageName + ':latest', './support/turbine')
//
//                 accountServiceImage.push()
//                 analyticsServiceImage.push()
//                 orderServiceImage.push()
//                 quotesProviderServiceImage.push()
//
//                 authServiceImage.push()
//                 feignServiceImage.push()
//                 gatewayServiceImage.push()
//                 tracingServiceImage.push()
//                 turbineServiceImage.push()
//
//                 }
//             }
//         }
//     }

    stage('Deploy') {
        steps {
             script {

              sh 'kubectl apply -f ./mongodb/mongodb-secret.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./mongodb/mongodb-deployment.yaml --kubeconfig=../../kubeconfig/config'

              sh 'kubectl apply -f ./kafka/kafka-deployment.yaml --kubeconfig=../../kubeconfig/config'

              sh 'kubectl apply -f ./configuration/kubernetes/account/account-configmap.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/analytics/analytics-configmap.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/auth/auth-configmap.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/feign/feign-configmap.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/gateway/gateway-configmap.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/order/order-configmap.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/quotes-provider/quotes-provider.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/cluster-rbac.yaml --kubeconfig=../../kubeconfig/config'
              sh 'kubectl apply -f ./configuration/kubernetes/ingress.yaml --kubeconfig=../../kubeconfig/config'

              sh 'sleep 7s'

                sh 'kubectl apply -f ./core/account-service/deploy.yaml --kubeconfig=../../kubeconfig/config'
                sh 'kubectl apply -f ./core/order-service/deploy.yaml --kubeconfig=../../kubeconfig/config'
                sh 'kubectl apply -f ./core/analytics-service/deploy.yaml --kubeconfig=../../kubeconfig/config'
                sh 'kubectl apply -f ./core/quotes-provider-service/deploy.yaml --kubeconfig=../../kubeconfig/config'

                sh 'kubectl apply -f ./support/auth/deploy.yaml --kubeconfig=../../kubeconfig/config'
                sh 'kubectl apply -f ./support/feign-hystrix-client/deploy.yaml --kubeconfig=../../kubeconfig/config'
                sh 'kubectl apply -f ./support/gateway/deploy.yaml --kubeconfig=../../kubeconfig/config'
                sh 'kubectl apply -f ./support/tracing/deploy.yaml --kubeconfig=../../kubeconfig/config'
             }

         }
    }

    }
   }