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
}
    agent { label 'master'}
    stages {

//     stage('Checkout code') {
//           steps{
//             cleanWs()
//             git branch: 'spring-cloud-k8s-native',
//             credentialsId: 'GitHub',
//             url: "https://github.com/VitaliiRubezhanskii/financial-stock-broker-2.git"
//         }
//     }
//
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

//               sh 'kubectl apply -f ./core/account-service/deploy.yaml'
//               sh 'kubectl apply -f ./core/order-service/deploy.yaml'
//               sh 'kubectl apply -f ./core/analytics-service/deploy.yaml'
//               sh 'kubectl apply -f ./core/quotes-provider-service/deploy.yaml'
//
//             sh 'kubectl apply -f ./support/auth/deploy.yaml'
//             sh 'kubectl apply -f ./support/feign-hystrix-client/deploy.yaml'
//             sh 'kubectl apply -f ./support/gateway/deploy.yaml'
//             sh 'kubectl apply -f ./support/tracing/deploy.yaml'
            sh 'envsubst < ${WORKSPACE}/deploy.yaml | kubectl apply -f -'


             }

         }
    }

    }
   }