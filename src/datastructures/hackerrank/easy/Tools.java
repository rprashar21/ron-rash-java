package datastructures.hackerrank.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tools {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("fork", "barbie", "japan", "happen", "gate");

        int fork = toolchanger(stringList, 2, "fork");
        System.out.println(fork);
    }

    public static int toolchanger(List<String> tools, int startIndex, String target) {

        int counter=0;
        int anotherCounter=0;
        // Loop through the list starting from the startIndex
        for (int i = startIndex; i < tools.size(); i++) {
            counter++; // Increment the counter for each move

            // Check if the current tool matches the target tool
            if (tools.get(i).equalsIgnoreCase(target)) {
               break; // Return the number of moves made so far
            }
        }

        for (int i = 0; i < startIndex; i++) {
            anotherCounter++; // Increment the counter for each move

            // Check if the current tool matches the target tool
            if (tools.get(i).equalsIgnoreCase(target)) {
        break; // Return the number of moves made so far
            }
        }
        if(counter <=anotherCounter)
        {
            return counter;
        }
        else
        return anotherCounter;
    }

}
