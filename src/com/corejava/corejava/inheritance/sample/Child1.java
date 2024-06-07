package com.corejava.corejava.inheritance.sample;

public class Child1 extends Parent {

    String name;

    public Child1(final String dna, final Address address, final String title) {
        super(dna, address, title);
    }

    public Address getAddress() {
        System.out.println("This is not his actaul addres");
        Address address1 = super.address;

        address1.setStreet("naya stree");

        return address1;
    }
}
