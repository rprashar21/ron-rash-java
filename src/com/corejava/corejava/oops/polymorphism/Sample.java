package com.corejava.corejava.oops.polymorphism;

public class Sample {

    // Polymorphism in Java is a concept by which we can perform a single action in different ways.
//    In this example, we are creating two classes Bike and Splendor.
//    Splendor class extends Bike class and overrides its run() method.
//    We are calling the run method by the reference variable of Parent class.
//    Since it refers to the subclass object and subclass method overrides the Parent class method, the subclass method is invoked at runtime.
//    Since method invocation is determined by the JVM not compiler, it is known as runtime polymorphism.

    // A method is overridden, not the data members, so runtime polymorphism can't be achieved by data members.

    // static binding -- when object is determined at compile time it is known as static binding
    // If there is any private, final or static method in a class, there is static binding.
    // dynamic -- when object is determined at runtime it is known as dynamic binding
}
class Bike{

    int speed=90;
    void run()
    {
        System.out.println("running");
    }
}
class Splendor extends Bike{

    int speed=110;
    void run(){
        System.out.println("running safely with 60km");
    }

    public static void main(String args[]){
        Bike b = new Splendor();//upcasting
        b.run();
        System.out.println(b.speed); // this will give not becoz data members are not overridden
    }
}