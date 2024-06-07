# create a provider
#https://github.com/stacksimplify/azure-aks-kubernetes-masterclass/blob/master/24-Azure-AKS-Terraform/24-01-Terraform-Commands-Basics/README.md

provider "azurerm"{
  version = "2.35.0"
}

# create a resource              # this name is for local reference
resource "azurerm_resource_group" "aks_dev_local_name" {
  location = "Central UK"
  name     = "aks_dev10"
}

tags ={
  envirnoment ="k8sukmojenv"
}

# terraform validate, terraform plan , terraform apply
# terraform refresh -> update the local state against the real state in cloud means if we have made any changes diretly to the cloud manually it will pull
# those changes also and let us know about it. now if there is change how to know that > terrafrom referesh -- see the change and apply it manually
# desired state -> state which ur aiming and its in ur local tf files
# current state  -> current statet in ur cloud
# order of eexcution  -> refresh planmake a descison   and then apply

# terraform show  -> shows the current state or plan
# terraform destroy   -> destroys the resource in cloud. it will a
# terraform destroy --help -> terraform destroy -target= azurerm_resource_group.aks_dev_local_name


# https://github.com/stacksimplify/azure-aks-kubernetes-masterclass/blob/master/24-Azure-AKS-Terraform/24-02-Terraform-Language-Basics/README.md
# about terraform settings block - min terraform version required to create ur resource in cloud
#We primarily define the below 3 items in Terraform Settings Block
#Terraform Version
#Terraform Providers
#Azure RM Provider
#Azure AD Provider -- only admins can have access to ur aks cluster or some resources
#Random Provider -- unique value for analytical logs and stuff like that