provider "aws" {
  region  = var.awsprops.region
  profile = var.awsprops.profile
}

terraform {
  required_version = "~> 1.1.2"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
    template = {
      source  = "hashicorp/template"
      version = "~> 2.1"
    }
  }

  backend "s3" {
    bucket         = "fsb-terraform-remote-states"
    region         = "eu-central-1"
    dynamodb_table = "tf-locks"
    encrypt        = true
    profile        = "default"
  }
}
