resource "azurerm_resource_group" "aks_rg" {
  location = var.location
  name     = var.resource_group_name
}