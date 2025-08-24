package com.corejava.corejava.datatype;

import java.util.ArrayList;
import java.util.List;

public class Wrapperss {

    // All the wrapper classes extends Number implements Comparable<Integer>
    //Why Did Java Introduce Wrapper Classes?
    //✅ Reason:
    //
    //Java is an object-oriented language, but primitive types (int, double, etc.) are not objects.
    //
    //Collections (List, Set, etc.) require objects, not primitives.
    //
    //To allow primitives to work with OOP concepts like generics, reflection, and collections, Java introduced wrapper classes (e.g., Integer, Double, etc.).

   // Autoboxing and Unboxing automatically convert between primitive and wrapper types.

    //No. Collections like List<int> are not allowed

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5); // Autoboxed from int to Integer
    }

}
