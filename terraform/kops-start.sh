#!/bin/bash
echo "export NAME=financial.broker.k8s.local" >> /home/ubuntu/.bashrc
echo "export KOPS_STATE_STORE=s3://fsb-kops-configs" >> /home/ubuntu/.bashrc

echo "export AWS_ACCESS_KEY_ID=$(aws configure get aws_access_key_id)" >> /home/ubuntu/.bashrc
echo "export AWS_SECRET_ACCESS_KEY=$(aws configure get aws_secret_access_key)" >> /home/ubuntu/.bashrc
source /home/ubuntu/.bashrc

kubectl --kubeconfig=/home/ubuntu/.kube/config apply -f /home/ubuntu/jenkins/workspaces/fsb-release-01_master/configuration/kubernetes/kops-admin.yaml
kubectl --kubeconfig=/home/ubuntu/.kube/config apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.5/aio/deploy/recommended.yaml
