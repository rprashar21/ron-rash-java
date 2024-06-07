package com.corejava.corejava.oops.Interface;

public class SampleInterfaceStaticMethods {

    // we can have static methods in interface

    // utility methods
    public static void main(String[] args) {
        System.out.println(SampleInterface01.calculate());
    }

}

interface SampleInterface01{

    // this is a staic method used as a utlity
    // cannit be overriden by any impelenation class becoz its static and doen not belog to object
    // we can main methods in interface and can run them directly from tehre
    public static int calculate(){
        System.out.println("Inside static method of interface");
        return 100;
    }
}