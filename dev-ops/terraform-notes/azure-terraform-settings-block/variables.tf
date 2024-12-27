# we can add cariables here

variable "location" {
  default = "Central Us"
  type = string
  description = "al aks resources will be created here"
}

variable "resource_group_name" {
  default = "aks_dev"
  type = string
  description = "resource group name"
}

variable "environment" {
  default = "dev"
  type = string
  description = "environment for dev"
}