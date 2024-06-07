package com.corejava.corejava.inheritance.sample;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {

        Address address1 = new Address(12,"rakoh","mandi","himachal pradesh");
        Address address2 = new Address(20,"chatsworth","guwhati","assam");

        Child1 child1 = new Child1("p123",address1,"prashar");
        Child1 child2 = new Child1("a234",address2,"acharya");

        System.out.println(child1.getAddress());

        System.out.println(child1.getTitle());
        System.out.println(child2.getTitle());


        // common attributes
        System.out.println(Parent.getMoney());
    }
}
