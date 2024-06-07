package datastructures.arrays.leetcodeArrays;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {

        int[] a = {3, 0, 6, 1, 5};
        hIndex(a);
    }
    public static int hIndex(int[] a) {
        // Sort the citations in non-increasing order
        Arrays.sort(a);
        int h = 0; // This will store the maximum h-index
        // Iterate over the citations from highest to lowest
        for (int i = a.length - 1; i >= 0; i--) {
            // Check if the current citation is greater than or equal to the position
            int count = a.length - i;
            if (a[i] >= count) {
                h = count; // Update the h-index
            } else {
                break; // If the condition is not satisfied, break the loop
            }
        }
        System.out.println(h);
        return h;
    }
}
