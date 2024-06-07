package datastructures.arrays.Questions;

import java.util.Arrays;

public class KthElement {

    // Write a program to find the kth largest element in an unsorted array of integers

    // array [10,4,9,7,3,1,20,2] -- 3rd largest 1

    //1 sort the array and return what has been asked

    public static void main(String[] args) {
        int[] array = new int[]{10,4,9,7,3,1,20,2};
        int k =2; // 4th largest

        System.out.println(findKthLargestElement(array,k));
    }

    private static int findKthLargestElement(final int[] array,int k) {

        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        return array[array.length-k];
    }
}
