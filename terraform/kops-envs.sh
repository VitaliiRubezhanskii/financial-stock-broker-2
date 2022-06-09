#!/bin/bash
echo "export NAME=stockbroker.vrubizhanskyi.com" >> ~/.bashrc
echo "export KOPS_STATE_STORE=s3://fsb-kops-configs" >> ~/.bashrc

echo "export AWS_ACCESS_KEY_ID=$(aws configure get aws_access_key_id)" >> ~/.bashrc
echo "export AWS_SECRET_ACCESS_KEY=$(aws configure get aws_secret_access_key)" >> ~/.bashrc

source ~/.bashrc
