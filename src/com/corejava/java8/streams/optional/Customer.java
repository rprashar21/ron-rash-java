package com.corejava.java8.streams.optional;

import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<Integer> phoneNumbers;

    public Customer(final int id, final String name, final List<Integer> phoneNumbers) {
        this.id = id;
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPhoneNumbers(final List<Integer> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
