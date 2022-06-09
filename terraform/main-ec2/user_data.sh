#!/bin/bash
# install software
sudo groupadd docker
sudo gpasswd -a ubuntu docker
sudo usermod -aG docker ubuntu
sudo su - ubuntu

sudo file -s /dev/xvda
sudo mkfs -t ext4 /dev/xvda
sudo mkdir /volume
sudo mount /dev/xvda /volume/

sudo apt-get update
sudo dpkg --configure -a
echo "yes" | sudo apt install openjdk-8-jre-headless

sudo apt-get install awscli -y
sudo apt-get install -y awslogs
sudo apt-get install wget
sudo apt install git

echo "yes" | sudo apt-get install gradle
sudo apt-get install ca-certificates curl gnupg lsb-release
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

echo "yes" | sudo apt-get install docker-ce docker-ce-cli containerd.io

sudo chown root:docker /var/run/docker.sock
sudo chown -R root:docker /var/run/docker

sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

sudo wget  https://github.com/kubernetes/kops/releases/download/v1.20.0/kops-linux-amd64
sudo chmod a+x kops-linux-amd64
sudo mv kops-linux-amd64 /usr/local/bin/kops

sudo wget https://storage.googleapis.com/kubernetes-release/release/v1.20.0/bin/linux/amd64/kubectl
sudo chmod a+x kubectl
sudo mv kubectl /usr/local/bin/kubectl

sudo chmod +x /home/ubuntu/kops-envs.sh
sudo chmod +x /home/ubuntu/kops-start.sh