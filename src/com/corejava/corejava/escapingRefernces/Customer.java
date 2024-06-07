package com.corejava.corejava.escapingRefernces;

public class Customer implements CustomerInterface {
    private String name;

    public Customer(final String name) {
        this.name = name;
    }

    public Customer(Customer c) {
        this.name = c.getName();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "customer name: "+name;
    }
}
