package com.corejava.corejava.oops.abstraction;

public class SampleInterfaceStaticMethods {

    // we can have static methods in interface

    // utility methods
    public static void main(String[] args) {
        System.out.println(SampleInterface01.calculate());
    }

}

interface SampleInterface01{

    // this is a static method used as a utlity
    // cannot be overriden by any impelenation class becoz its static and doen not belog to object
    // we can main methods in interface and can run them directly from there
     static int calculate(){
        System.out.println("Inside static method of interface");
        return 100;
    }
}