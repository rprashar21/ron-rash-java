package com.corejava.corejava.inheritance.sample;

public class Parent {
    String dna;
    Address address;
    String title;

    public Parent(final String dna, final Address address, final String title) {
        this.dna = dna;
        this.address = address;
        this.title = title;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(final String dna) {
        this.dna = dna;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
    public static double getMoney()
    {
        return 200000;
    }
}
class Address{

    Integer houseNumber;
    String street;
    String city;
    String State;



    public Address(final Integer houseNumber, final String street, final String city, final String state) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        State = state;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(final Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return State;
    }

    public void setState(final String state) {
        State = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber=" + houseNumber +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", State='" + State + '\'' +
                '}';
    }
}