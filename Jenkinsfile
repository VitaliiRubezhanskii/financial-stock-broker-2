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

    stage('Checkout code') {
          steps{
            cleanWs()
            git branch: 'spring-cloud-k8s-native',
            credentialsId: 'GitHub',
            url: "https://github.com/VitaliiRubezhanskii/financial-stock-broker-2.git"
        }
    }

    stage('Build'){
        steps{
            script {
               sh 'mvn clean install -DskipTests=true'
            }
        }
    }

    stage('Docker image'){
        steps {
            script {
                docker.withRegistry('', registryCredential){

                def accountServiceImage = docker.build(accountServiceImageName + ":latest", '/var/jenkins_home/workspace/_service_spring-cloud-k8s-native/core/account-service')
                def analyticsServiceImage = docker.build(analyticsServiceImageName + ":latest", '/var/jenkins_home/workspace/_service_spring-cloud-k8s-native/core/analytics-service')
                def orderServiceImage = docker.build(orderServiceImageName + ":latest", 'var/jenkins_home/workspace/_service_spring-cloud-k8s-native/core/order-service')
                def quotesProviderServiceImage = docker.build(quotesProviderServiceImageName + ':latest', 'var/jenkins_home/workspace/_service_spring-cloud-k8s-native/core/quotes-provider-service')

                def authServiceImage = docker.build(authServiceImageName + ':latest', 'var/jenkins_home/workspace/_service_spring-cloud-k8s-native/support/auth')
                def feignServiceImage = docker.build(feignServiceImageName + ':latest', 'var/jenkins_home/workspace/_service_spring-cloud-k8s-native/support/feign-hystrix-client')
                def gatewayServiceImage = docker.build(gatewayServiceImageName + ':latest', 'var/jenkins_home/workspace/_service_spring-cloud-k8s-native/support/gateway')
                def tracingServiceImage = docker.build(tracingServiceImageName + ':latest', 'var/jenkins_home/workspace/_service_spring-cloud-k8s-native/support/tracing')
                def turbineServiceImage = docker.build(tracingServiceImageName + ':latest', 'var/jenkins_home/workspace/_service_spring-cloud-k8s-native/support/turbine')

                accountServiceImage.push()
                analyticsServiceImage.push()
                orderServiceImage.push()
                quotesProviderServiceImage.push()

                authServiceImage.push()
                feignServiceImage.push()
                gatewayServiceImage.push()
                tracingServiceImage.push()
                turbineServiceImage.push()

                }
            }
        }
    }

    stage('Deploy') {
        steps {
             script {

              sh 'kubectl apply -f /var/jenkins_home/workspace/financial_stock_broker/k8s/configserver-deploy.yaml'
              sh 'kubectl apply -f /var/jenkins_home/workspace/financial_stock_broker/k8s/discovery-deploy.yaml'

             }

         }
    }

    }
   }