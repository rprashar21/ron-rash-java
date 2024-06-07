package com.corejava.corejava.oops.abstraction;

public class SampleAbstract {

    // why abstraction --> where we cannot descibe what specific type it is like an animal --octupus/tiger both are animals and
    // java allows to group certain charesteristics into a group called abstraction

    // the base class is usually an abstract cncept-- the main purpose of the abstract class is to define behaviour its subclasses wil have
    // so it always participates in inheritance

    //A class which is declared as abstract is known as an abstract class.
    // abstarct class can have constructors because its subclasses will use that to initialize its data members
    // It can have abstract and non-abstract methods. It needs to be extended and its method implemented. It cannot be instantiated.
    // If there is an abstract method in a class, that class must be abstract.
    // If you are extending an abstract class that has an abstract method, you must either provide the implementation of the method or make this class abstract.
    // abstract class can also be used to provide some implementation of the interface.
//
//    An abstract class must be declared with an abstract keyword.
//    It can have abstract and non-abstract methods.
//    It cannot be instantiated.
//    It can have constructors and static methods also.
//    It can have final methods which will force the subclass not to change the body of the method.

    // if a class has an abstract method the class should be abstract

    // Diff betwee abstract and interface

    // inside interface every var is public static final      |       we can decalre var and methods in abstract
    // method is public and abstarct                          |   can be abstarct or non abstarct
    // cannot have constructors                               |   can have construtors
    // we cannot have static or instance blocks               |   we can ave
    // can refer lambdas                                      |   cannot refer lambdas
    // we canot override OBJECT CLASS METHOD                  | WE CAN OVERRIDE OBJECT CLASS METHODS

}

abstract class Sam{
     final String name="hello"; // an instance vraibale
    static final String adderss="chatsworth";
    protected int age=40; // subclasses can access

    public  final void calSum(){
        System.out.println("value is ");;
    }
}

abstract class Sam2 extends Sam{} // an abstarct class can extend another abstract class and also a concrete class

class SamAdd extends Sam{
    public static void main(String[] args) {
        SamAdd obj = new SamAdd();
        System.out.println(obj.age);
        System.out.println(obj.name);
        System.out.println(Sam.adderss);
        obj.calSum();
    }
}

abstract class A{
    // can or cannot have abstract methods

}
abstract class B{

   abstract void run();  // abstract method
}

class BB extends B{


    @Override
    void run() {
        /// since we have extended the abstract method we have to implement this
    }
}


abstract class C{

     void run(){
         System.out.println(" non abstract method in an abstract class with no abstract methods");
}  // non  abstract method
}

abstract class  Bike{

    private String name;
     Bike(String name){
         System.out.println("constructor in abstract ");
         this.name=name; // constructors are only used to intialize the data members
     }

}

class Honda extends A{}
class Hond1 extends B{
    @Override
    void run() {
        System.out.println("inside actual implementation class which extends the abstract method ");
    }
}

class Hond2 extends C{

}
