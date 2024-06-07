package com.corejava.corejava.oops.inheritance;

public class AggregateSample {

    // has a realtionship // aggregate
   // If a class have an entity reference, it is known as Aggregation. Aggregation represents HAS-A relationship.
}
class Employee{
    int id;
    String name;
    Address address;//Address is a class

}

class Address{
    private long postcode;
        }