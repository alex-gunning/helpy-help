provider "aws" {
   region = "eu-west-1"
}

module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "2.44.0"

  name = var.vpc_name
  cidr = var.vpc_cidr

  azs             = var.vpc_azs
  private_subnets = var.vpc_private_subnets
  public_subnets  = var.vpc_public_subnets

  enable_nat_gateway = var.vpc_enable_nat_gateway
  enable_dns_hostnames = true # for dev
  # default_security_group_ingress = [{
  #   protocol  = -1
  #   self      = true
  #   from_port = 3306
  #   to_port   = 3306
  # }]

  tags = var.vpc_tags
}

module "mysql_security_group" {
  source = "terraform-aws-modules/security-group/aws"
  version = "3.11.0"
  name        = "allow_db_remote_access"
  description = "Allow db development remote access"
  vpc_id      = module.vpc.vpc_id

  ingress_with_cidr_blocks = [{ 
    rule = "mysql-tcp"
    cidr_blocks = "0.0.0.0/0"
  }]
}

resource "aws_internet_gateway" "gw" {
  vpc_id = module.vpc.default_vpc_id

  tags = {
    Name = "main"
  }
}

module "db" {
  source  = "terraform-aws-modules/rds/aws"
  version = "~> 2.0"

  identifier = "demodb"

  engine            = "mysql"
  engine_version    = "5.7.19"
  instance_class    = "db.t2.micro"
  allocated_storage = 20

  publicly_accessible = true

  storage_encrypted = false

  # kms_key_id        = "arm:aws:kms:<region>:<accound id>:key/<kms key id>"
  name     = "demodb"
  username = "user"
  password = "password"
  port     = "3306"

  vpc_security_group_ids = [module.mysql_security_group.this_security_group_id]

  maintenance_window = "Mon:00:00-Mon:03:00"
  backup_window      = "03:00-06:00"

  # disable backups to create DB faster
  backup_retention_period = 0

  tags = {
    Owner       = "user"
    Environment = "dev"
  }

  # DB subnet group
  subnet_ids = module.vpc.public_subnets # for dev (NOT prod)

  # DB parameter group
  family = "mysql5.7"

  # DB option group
  major_engine_version = "5.7"

  # Database Deletion Protection
  deletion_protection = false

  parameters = [
    {
      name  = "character_set_client"
      value = "utf8"
    },
    {
      name  = "character_set_server"
      value = "utf8"
    }
  ]

  options = [
    {
      option_name = "MARIADB_AUDIT_PLUGIN"

      option_settings = [
        {
          name  = "SERVER_AUDIT_EVENTS"
          value = "CONNECT"
        },
        {
          name  = "SERVER_AUDIT_FILE_ROTATIONS"
          value = "37"
        },
      ]
    },
  ]
}