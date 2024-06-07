package datastructures.sorting.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllDuplicatesArray {
    public static void main(String[] args) {
        int[] a = {4, 3, 2, 7, 8, 2, 3, 1};
        //ans -- 2,3
        // do cyclic sort

        final List<Integer> allDuplicateNumbers = findAllDuplicateNumbers(a);
        System.out.println(allDuplicateNumbers);

    }

    private static List<Integer> findAllDuplicateNumbers(final int[] a) {
        int i = 0;
        List<Integer> duplicateList = new ArrayList<>();
        while (i < a.length) {
                int correctIndex = a[i] - 1;
                if (a[i] != a[correctIndex]) // checks for correct position
                    swap(a, i, correctIndex);
                else
                    i++;

        }
        System.out.println(Arrays.toString(a));
        // find all the elements out of place and put in list
        for(int j=0;j< a.length;j++)
        {
            if(a[j]!=j+1)
                duplicateList.add(a[j]);
        }
        return duplicateList;
    }

    private static void swap(final int[] a, final int i, final int correctIndex) {
        int temp = a[i];
        a[i] = a[correctIndex];
        a[correctIndex] = temp;
    }
}
