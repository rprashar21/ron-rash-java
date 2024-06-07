package com.corejava.java8.streams;

public class Address {


    private String postcode;
    private String state;
    private String city;

    public Address(final String postcode, final String state, final String city) {
        this.postcode = postcode;
        this.state = state;
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }
}
