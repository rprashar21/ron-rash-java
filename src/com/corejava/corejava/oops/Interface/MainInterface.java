package com.corejava.corejava.oops.Interface;

public class MainInterface
{
    public static void main(String[] args) {
        Child child = new Child();
        System.out.println(child.add());
        // this is the static method
        SomeInterface.getValue();

        child.getAdd();

    }
}
