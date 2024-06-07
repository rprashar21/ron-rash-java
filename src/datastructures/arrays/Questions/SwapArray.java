package datastructures.arrays.Questions;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SwapArray {
    public static void main(String[] args) {
        //swap or reverse an array

        int[] arr = {1,3,2,9,23}; // 23,9,2,31,1

        int start=0;
        int end =arr.length-1;

        while(start<=end)
        {
            swap(arr,start,end);
            start++;
            end--;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(final int[] arr,int start,int end) {

        int temp =arr[start];
        arr[start]=arr[end];
        arr[end]=temp;
    }
}
