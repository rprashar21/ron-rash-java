package com.corejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImmutableList {
    public static void main(String[] args) {


        // Before Java 9

        List<String> list = Arrays.asList("a","b"); // creates an unmodifiable list
        System.out.println(list);
        List<String> list2 = new ArrayList<>();
        list2.add("apples");

      list2=  Collections.unmodifiableList(list2);
        System.out.println(list2);
     list2.add("aasddds");
     list.add("mmm");
        System.out.println(list2);
    }
}
