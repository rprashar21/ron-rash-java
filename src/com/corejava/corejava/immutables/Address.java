package com.corejava.corejava.immutables;

public class Address {
    private int code;
    private String city;

    public Address(final int code, final String city) {
        this.code = code;
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "code=" + code +
                ", city='" + city + '\'' +
                '}';
    }
}
