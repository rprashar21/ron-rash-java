package com.corejava.corejava.comparableandcomparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FixedListSortingExample {
    public static void main(String[] args) {
        String[] array = {"Adjourned", "Bail with conditions",
                "Bail without conditions", "Bail", "Remand", "Warrant", "Order",
                "Final Sentence", "Bail Conditions Cancelled", "Warrant Withdrawn"};

        String[] input = {"finalsentence", "order", "Adjourned"};

        // Create a map that assigns each value in the fixed list an index
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            indexMap.put(array[i].toLowerCase(), i);
        }

        // Sort the input values based on their order in the fixed list using a custom comparator
        Arrays.sort(input, Comparator.comparingInt(value -> indexMap.getOrDefault(value.toLowerCase(), Integer.MAX_VALUE)));

        // Print the sorted input values
        for (String value : input) {
            System.out.println(value);
        }
    }
}

