------------Understanding variables in terraform -------

Variables are important in TerraForm bcz it makes our scrpit dynamic


Creating a new Project
 --> new project ,,
--> terraform init


terraform apply
terraform console -- to play with the Console
> var.names
> length(var.names)
> reverse(var.names)
check distinct
> distinct(var.names)
> toset(var.names)
> concat(var.names,["new_value"]) -- concat with a new list
> contains(var.names,"ravi")
  false
> sort(var.name)
> range (1,3) -- 1,2
> range(1,3,4)



code ---

variable "names"{
default = ["rohit","james",swati]    // creating a list of names
}

provider{
aws = {
     source  = "hashicorp/aws"
     version = "~> 3.0"
   }
}

resource "aws_iam_user" "my_local_user"{
count = length(var.names)
name= var.names[count.index]
}


If we use count the deletions and updations on the list wil be based on the index
but if we use for_each it will be used on value that is present ,, this is becoz how terraform internally stores the state

variable "names"{
  #default =["rohit","james","swati"]
  default =["newUser1","ravs","rohit","james","swati"]
}

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"
    }
  }
}

resource "aws_iam_user" "my_rondev2_users"{
  #count = length(var.names)  // count will be the length of the names list
  #name = var.names[count.index]
  for_each = toset(var.names)
  name = each.value

}
