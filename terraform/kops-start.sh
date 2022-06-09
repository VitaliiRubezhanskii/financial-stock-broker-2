#!/bin/bash
source ~/.bashrc

kops create cluster --name=financial.broker.k8s.local --state=s3://fsb-kops-configs --node-size=t3.small --node-count=1 --master-size=t3.small --master-count=1 --cloud=aws --zones=eu-central-1a --yes
