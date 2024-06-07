package com.corejava.java9;

import java.util.List;
import java.util.Set;


public class ImmutableCollections {

    public static void main(String[] args) {
        //We can create immutable collections in java

        // this llist should not be null and should not contain null values
        final List<String> list = List.of("abc", "xyz");

        // if i try to add here it will throw illegal argument exception
        list.add("zzz");

        //Same for Set as well cannot add duplicate


    }
}
