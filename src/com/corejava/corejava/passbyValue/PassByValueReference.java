package com.corejava.corejava.passbyValue;

public class PassByValueReference {


    /*
    *  java is alwasy pass by value and not reference
    * var we pass the copy of the variable and not the
    *  objects -- we pass the pointer reference ie copy of the pointer reference and that changes the object values
    *  final -- actually means that the variable can be assigned only once
    *   class --> cannot be subclassed
    *    method --> cannot be overrriden
    *    var --> constant
    * */
    public static void main(String[] args) {
       final Customer c;
        c= new Customer("Jack");
      //  c= new Customer("Jill"); --> this code will give compiler error bcz of the final keyword
    }
}
class Customer{
    private String name;
   public Customer(String name)
   {
       this.name=name;
   }
}