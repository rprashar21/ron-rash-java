package datastructures.arrays.arrayQuestionsleetcode;

import java.util.Arrays;
import java.util.List;

public class ShortestWordDistanceSample {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a0","a1","a2","a3","a4","a5");
        int target = circularTarget(stringList, "a0", 4);
        System.out.println("target is "+target);

    }
    public static int circularTarget(List<String> words,
                                     String target,
                                     int startIndex)
    {
        // Get the size of the list of words
        int n = words.size();

        // Initialize the distance to the right to 0
        int rightDistance = 0;

        // Loop indefinitely until the target is found or
        // the entire list is searched
        for (int i = startIndex; true; i = (i + 1) % n) {
            // Check if the entire list has been searched
            // without finding the target
            if (rightDistance == n) {
                return -1;
            }

            // Check if the current word is equal to the
            // target
            if (words.get(i).equals(target)) {
                // Break out of the loop if the target is
                // found
                break;
            }
            else {
                // Increment the distance to the right and
                // move to the next position
                rightDistance++;
            }
        }

        // Initialize the distance to the left to 0
        int leftDistance = 0;

        // Loop indefinitely until the target is found or
        // the entire list is searched
        for (int i = startIndex; true;
             i = (i - 1 + n) % n) {
            // Check if the current word is equal to the
            // target
            if (words.get(i).equals(target)) {
                // Break out of the loop if the target is
                // found
                break;
            }
            else {
                // Increment the distance to the left and
                // move to the previous position
                leftDistance++;
            }
        }

        // Return the minimum distance between the start
        // index and the target to the left and right
        return Math.min(rightDistance, leftDistance);
    }
}
