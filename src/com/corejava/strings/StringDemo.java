package com.corejava.strings;

public class StringDemo {
    public static void main(String[] args) {
        // BAD: + in loop
        String result = "";
        for (int i = 0; i < 5; i++) {
            result = result + i;
        }
        System.out.println("Using +: " + result);

        // GOOD: StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i);
        }
        System.out.println("Using StringBuilder: " + sb);

        // String pool demo
        String s1 = "rohit";
        String s2 = "rohit";
        String s3 = new String("rohit");
        String s4 = s3.intern(); // intern() forces reference to the pooled instance

        System.out.println(s1 == s2); // true (pool)
        System.out.println(s1 == s3); // false (heap vs pool)
        System.out.println(s1 == s4); // true (interned)
    }
}

