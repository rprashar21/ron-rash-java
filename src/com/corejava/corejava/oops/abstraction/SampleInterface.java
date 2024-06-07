package com.corejava.corejava.oops.abstraction;

public class SampleInterface {

    // inteface blueprint of class -- 100% abstraction and multiple inheitance also an is-a relationship

    // below point is most important --->
    // all methods are public and abstract by default and data members are public static final
    // after java 8 we can have default and static methods in an interface
    // Java 9, we can have private methods in an interface.

    //Why interface 100% abstraction , multiple inheritance and loose coupling
    // in java 8 we can have default methods or methods can have body but need to have default keyword
    // why do we need this default method -- this is used so that any class that implements the interface can have their own implementation
    // the interface does not force it upon all the impementation classes

    public static void main(String[] args) {
        Exam exam = new Exam();
        // access the data member;
        int age1 = Samp.age;
        // this default method is acessible to the class , we can also override this method
        exam.calculate();

        Test test = new Test();
        test.calculate(); // now this will call th eoverriden method
    }
}

interface Samp{
    // data members are public static final
    // methods are public abstract
     int age=10;
     void m1();
    // default methods
    default void calculate(){
        System.out.println("default cal method ");
    }
}

class Exam implements Samp{
    @Override
    public void m1() {

    }
}

class Test implements Samp{

    @Override
    public void m1() {

    }

    @Override // overriding the default method
    public void calculate() {
        System.out.println("overriden method id called when we override the default method of interface");
    }
}