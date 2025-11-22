package com.corejava.java8.lambdas;

import java.util.Comparator;

public class SampleLambda {

    /*
    In Java, a lambda expression like () -> { ... } is compiled into a synthetic class
    that implements the target functional interface (Runnable in this case).
The JVM creates an instance of this synthetic class to represent ioTask.
This instance is an object stored in the heap.



    * lambda expression is an anonymous function -- no name of function ,no return type and no modifiers
    * it i used to hold the functional interface
    * public int getLength(String s)
    * {
    * return s.length();
    * }
    *
    * // anonymous function  (s)->return s.length() or (s)-> s.length();
    *
    *  function interface -- sam single abstract method
    *   Comparaable and compratar are functional Interfs
    *   they have only one

A lambda expression is just a tiny, unnamed chunk of code you can pass around and run later.

    // Old way with an anonymous class:
 Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};

// With a lambda:
Runnable r = () -> System.out.println("Hello");


   Why Lambdas
    Less Boilerplate
You don’t have to write new MyInterface() { … } and override methods every time. Lambdas make code shorter and clearer


How are lambdas different from object s
Objects (instances of classes) require a class definition. Even anonymous classes need a “hidden” class file.

Lambdas look like objects (they implement an interface), but you don’t see or write that class yourself.
The JVM generates it automatically behind the scenes, so you get a runnable “object” without the noise




    *
    * */


}
