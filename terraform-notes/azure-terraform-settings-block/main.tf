# https://github.com/stacksimplify/azure-aks-kubernetes-masterclass/blob/master/24-Azure-AKS-Terraform/24-02-Terraform-Language-Basics/README.md
# about terraform settings block - min terraform version required to create ur resource in cloud
#We primarily define the below 3 items in Terraform Settings Block
#Terraform Version
#Terraform Providers
#Azure RM Provider
#Azure AD Provider -- only admins can have access to ur aks cluster or some resources
#Random Provider -- unique value for analytical logs and stuff like that

# Lets Define the terrafrom settings block
terraform {
  # terraform version
  # terraform providers

  required_version = ">=0.13"
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = ">=3.90.0"
    }
    azuread = {
      source  = "hashicorp/azurerm"
      version = ">=3.90.0"
    }
    random = {
      source  = "hashicorp/azurerm"
      version = ""
    }
  }
}

# 2, terrraform provider block
#Define Azure Resource Manager Features Block
# This block is required for azurerm 2.x
provider azurerm {
  # v2.x azurerm requires "features" block
  features {}
}
# Create Random pet resource
resource "random_pet" "aksrandom" {

}

# Update main.tf with Terraform State Storage Configure Terraform State Storage
terraform {
  backend "azurerm" {
    resource_group_name   = "terraform-storage-rg"
    storage_account_name  = "terraformstatexlrwdrzs"
    container_name        = "tfstatefiles"
    key                   = "terraform.tfstate"
  }
}

# Terraform Initialize
#terraform init