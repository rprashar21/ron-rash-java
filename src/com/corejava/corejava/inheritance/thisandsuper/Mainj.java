package com.corejava.corejava.inheritance.thisandsuper;

public class Mainj {


    public static void main(String[] args) {

        Child child = new Child();
        child.childMethod();

        Child newChild = new Child("ron","england");


        System.out.println(  newChild.getAddress());
        System.out.println(  newChild.getName());
        child.printParent();
        int parentAge = child.parentAge; // we can access the public / protected parent methods and variables
        // we can also override them
    }
}
