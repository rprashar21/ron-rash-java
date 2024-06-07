package datastructures.sorting;

import java.util.Arrays;

public class cyclicSort {
    public static void main(String[] args) {


        //sort an array of a rang 1----10 -- where numbers are not sorted

       // int[] array = {3, 5, 2, 1, 4};
        int[] array = {3,2,1};
        cyclicSorting(array);
    }

    private static void cyclicSorting(final int[] array) {


        int i = 0;
        while (i< array.length) {
            int correctIndex = array[i] - 1;
            if(array[i]!=array[correctIndex])
            {
                int temp = array[i];
                array[i] = array[correctIndex];
                array[correctIndex] = temp;
            }
            else {
                i++;
            }

        }

        System.out.println(Arrays.toString(array));
    }
}
