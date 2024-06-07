package com.corejava.corejava.oops.inheritance;
/*
* whatever is present to the child is not presenet to the parent
* is a relationship , code reusablity
*
*
* */
public class Parent {
    private String name;
    public Parent(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
class Child extends Parent{

    private int age;

    public Child(final String name, final int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}