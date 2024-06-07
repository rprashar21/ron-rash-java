package com.corejava.corejava.oops.thisvsSuper;

public class SampleSuper {

    // super is keyword to access immediate parent class instance variable or methods
    // super() method to invoke immediate parent class constructor.
    // super() is added in each class constructor automatically by compiler if there is no super() or this().
    String colour ="green";

    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(dog.colour);

        // using super keyword we can access parent class methods and keyword
    }
}

class Animal {
    String colour ="black"; // default access specifier

    public void hello()
    {
        System.out.println(colour);
        System.out.println("animal class");
    }

}

class Dog extends Animal{
    String colour ="green";
    public void print()
    {
        System.out.println(super.colour);
        super.hello();
    }
}