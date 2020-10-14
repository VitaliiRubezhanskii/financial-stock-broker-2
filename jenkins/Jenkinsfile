pipeline {
 environment {
    discoveryServiceRegistry = "vitalii1992/discovery"
    configserverServiceRegistry = "vitalii1992/configserver"
    registryCredential = 'DockerHub'
}
    agent { label 'master'}
    stages {

    stage('Checkout code') {
          steps{
            cleanWs()
            git credentialsId: 'GitHub', url: "https://github.com/VitaliiRubezhanskii/financial-stock-broker-2.git"
        }
    }

    stage('Build'){
        steps{
            script {
               sh 'mvn clean install'
            }
        }
    }

    stage('Docker image'){
        steps {
            script {
                docker.withRegistry('', registryCredential){
                def discoveryServiceImage = docker.build(discoveryServiceRegistry + ":latest", '/var/jenkins_home/jobs/financial_stock_broker/workspace/support/monitoring')
                def configserverServiceImage = docker.build(configserverServiceRegistry + ":latest", '/var/jenkins_home/jobs/financial_stock_broker/workspace/support/config')
                discoveryServiceImage.push()
                configserverServiceImage.push()
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