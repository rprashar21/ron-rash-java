package com.corejava.Generics.assignment2;

public class Printer<T> {

    private String name;

    public Printer(final String name) {
        this.name = name;
    }

    public <T> void print(T type)
    {
        System.out.println(name+" "+type);
    }
}
