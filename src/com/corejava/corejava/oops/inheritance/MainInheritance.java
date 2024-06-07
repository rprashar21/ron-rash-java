package com.corejava.corejava.oops.inheritance;

public class MainInheritance {

    public static void main(String[] args) {
        Child child = new Child("rohit", 30);
         child.getAge();

        // On parent reference we can call the parent methods,not child specific methods
        // parent reference can hold chld objects
        // child reference cannot be used to hold parent object
        Parent parent = new Child("rohit", 30);
        parent.getName();

        // On parent refere

       // Child child1= new Parent(""); -- this will give error incompatible type Parent cannot be converted to child
    }
}
