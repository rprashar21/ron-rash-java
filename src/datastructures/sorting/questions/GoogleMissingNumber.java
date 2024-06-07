package datastructures.sorting.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleMissingNumber {
    public static void main(String[] args) {

        // Question -- find out the missing numbers in an array of range of sequential numbers 1-n
        int a[]={7,3,2,4,8,2,3,1};

        //here there are duplicate numbers as well

        // same cycylic sort -- bcoz range 1-n + sorted

        final List<Integer> missingNumbers = findMissingNumbers(a);
        System.out.println(Arrays.toString(a));
        System.out.println(missingNumbers);

    }

    private static List<Integer> findMissingNumbers(final int[] a) {

        int i=0;
        List<Integer> missingNumberList = new ArrayList<>();
        while(i<a.length)
        {
            int correctIndex = a[i]-1;
            if(a[i]!=a[correctIndex])
                swap(a,i,correctIndex);
            else
                i++;
        }
        // find the missing elements becoz now the array is sorted with elements in their correct position
        // if the element is not at its correct posistion then it is missing number and add the index
        for(int j=0;j<a.length;j++)
        {
            if(a[j]-1!=j)
                missingNumberList.add(j+1);

        }
        return missingNumberList;
    }

    private static void swap(final int[] a, final int i, final int correctIndex) {
        int temp=a[i];
        a[i]=a[correctIndex];
        a[correctIndex]=temp;
    }

}
