variable "awsprops" {
  type = map(any)
  default = {
    region          = "eu-central-1"
    vpc             = ""
    main_vpc_cidr   = "10.0.0.0/24"
    public_subnets  = "10.0.0.128/26"
    private_subnets = "10.0.0.192/26"
    ami             = "ami-042ad9eec03638628"
    itype           = "t2.micro"
    subnet          = ""
    publicip        = true
    keyname         = "fsb-ec2-keys"
    secgroupname    = "FSB-Sec-Group"
    profile         = "default"
  }
}