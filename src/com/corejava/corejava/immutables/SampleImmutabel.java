package com.corejava.corejava.immutables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SampleImmutabel
{

    // feilds shoudl be private {private scope} and final becozonce the intialized the value cannot be changed
    // make sure nt return the original refernces for mutable obhects inside an Immutable object
    // no setters only getters
   // Caching and Memoization: Immutable objects are useful in caching and memoization scenarios,
    // where you want to store computed values without the risk of them being altered
    //Enums: Enums in Java are inherently immutable.
    //String ,Wrappers classes when you change the value of the Wrapper classes it creates new objects hence the wrapper classes are always immutable.
    //such as Collections.unmodifiableList(), Collections.unmodifiableSet(), and Collections.unmodifiableMap().
    // These collections prevent modifications after creation,

    public static void main(String[] args) {
        List<String> mutbaleList = new ArrayList<>();
        mutbaleList.add("apples");

        System.out.println(mutbaleList);

        List<String> stringList = Collections.unmodifiableList(mutbaleList);
        mutbaleList.add("banana"); // the original list is still mutbale ;
        System.out.println(mutbaleList);
        System.out.println(mutbaleList);
        stringList.add("oranges"); // this is now a immutable list ,, this will throw an error UnsupportedException


        String originalString = "Hello, World!";
        String upperCaseString = originalString.toUpperCase();

        System.out.println(originalString); // original string remains unchanged

    }
}
