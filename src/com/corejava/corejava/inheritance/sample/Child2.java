package com.corejava.corejava.inheritance.sample;

public class Child2 extends Parent{

    String name;

    public Child2(final String dna, final Address address, final String title, final String name) {
        super(dna, address, title);
        this.name = name;
    }
}
