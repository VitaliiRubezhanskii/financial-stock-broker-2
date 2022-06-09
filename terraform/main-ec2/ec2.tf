
//Create the VPC
resource "aws_vpc" "fsb-main-vpc" {                            # Creating VPC here
  cidr_block       = var.awsprops.main_vpc_cidr  # Defining the CIDR block use 10.0.0.0/24 for demo
  instance_tenancy = "default"
  enable_dns_hostnames = true
}
//Create Internet Gateway and attach it to VPC
resource "aws_internet_gateway" "IGW" { # Creating Internet Gateway
  vpc_id = aws_vpc.fsb-main-vpc.id              # vpc_id will be generated after we create VPC
}
//Create a Public Subnets.
resource "aws_subnet" "publicsubnets" { # Creating Public Subnets
  vpc_id     = aws_vpc.fsb-main-vpc.id
  cidr_block = var.awsprops.public_subnets # CIDR block of public subnets
}

//Route table for Public Subnet's
resource "aws_route_table" "PublicRT" { # Creating RT for Public Subnet
  vpc_id = aws_vpc.fsb-main-vpc.id

  route {
    cidr_block = "0.0.0.0/0" # Traffic from Public Subnet reaches Internet via Internet Gateway
    gateway_id = aws_internet_gateway.IGW.id
  }
}

//Route table Association with Public Subnet's
resource "aws_route_table_association" "PublicRTassociation" {
  subnet_id      = aws_subnet.publicsubnets.id
  route_table_id = aws_route_table.PublicRT.id
}


resource "aws_security_group" "project-fsb-sg" {
  name        = var.awsprops.secgroupname
  description = var.awsprops.secgroupname
  vpc_id      = aws_vpc.fsb-main-vpc.id

  // To Allow SSH Transport
  ingress {
    from_port   = 22
    protocol    = "tcp"
    to_port     = 22
    cidr_blocks = ["0.0.0.0/0"]
  }

  // To Allow Port 80 Transport
  ingress {
    from_port   = 80
    protocol    = "tcp"
    to_port     = 80
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_instance" "fsb-main-ec2" {
  ami                         = var.awsprops.ami
  instance_type               = var.awsprops.itype
  subnet_id                   = aws_subnet.publicsubnets.id
  associate_public_ip_address = var.awsprops.publicip
  key_name                    = var.awsprops.keyname
  user_data = data.template_file.ec2_userdata.rendered
  vpc_security_group_ids = [aws_security_group.project-fsb-sg.id]

  provisioner "file" {
    source      = "../../jenkins"
    destination = "/home/ubuntu"

    connection {
      type        = "ssh"
      host        = aws_instance.fsb-main-ec2.public_ip
      user        = "ubuntu"
      private_key = file("fsb-ec2-keys.pem")
    }
  }

  provisioner "file" {
    source      = "../../docker-compose.jenkins.yml"
    destination = "/home/ubuntu/docker-compose.jenkins.yml"

    connection {
      type        = "ssh"
      host        = aws_instance.fsb-main-ec2.public_ip
      user        = "ubuntu"
      private_key = file("fsb-ec2-keys.pem")
    }
  }

  provisioner "file" {
    source      = "/Users/vitalii/Documents/01.FSB/financial-stock-broker-2/terraform/kops-start.sh"
    destination = "/home/ubuntu/kops-start.sh"
    connection {
      type        = "ssh"
      host        = aws_instance.fsb-main-ec2.public_ip
      user        = "ubuntu"
      private_key = file("fsb-ec2-keys.pem")
    }
  }

  provisioner "file" {
    source      = "/Users/vitalii/Documents/01.FSB/financial-stock-broker-2/terraform/kops-envs.sh"
    destination = "/home/ubuntu/kops-envs.sh"

    connection {
      type        = "ssh"
      host        = aws_instance.fsb-main-ec2.public_ip
      user        = "ubuntu"
      private_key = file("fsb-ec2-keys.pem")
    }
  }

  provisioner "file" {
    source      = "/Users/vitalii/.aws"
    destination = "/home/ubuntu/.aws"

    connection {
      type        = "ssh"
      host        = aws_instance.fsb-main-ec2.public_ip
      user        = "ubuntu"
      private_key = file("fsb-ec2-keys.pem")
    }
  }

    root_block_device {
    delete_on_termination = true
    volume_size           = 20
    volume_type           = "gp2"
  }
  tags = {
    Name        = "MAIN_FSB_SERVER"
    Environment = "DEV"
    OS          = "UBUNTU"
    Managed     = "IAC"
  }

  depends_on = [aws_security_group.project-fsb-sg]
}

data "template_file" "ec2_userdata" {
  template = file("user_data.sh")
}


output "ec2instance" {
  value = aws_instance.fsb-main-ec2.public_ip
}