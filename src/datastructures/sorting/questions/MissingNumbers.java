package datastructures.sorting.questions;

import java.util.Arrays;

public class MissingNumbers {

    public static void main(String[] args) {

        // n distinct numbers find range [o,n] find the only number in the range which is missing
        //o(n) running time complexity and o(1) space time complexity
        int[] a= {5,2,4,1};


        final int missingNumber = findMissingNumber(a);
        System.out.println(missingNumber);

        int [] a1 = {1,2,4,5};  // i know that no of elements n=5
        // brute force
        missingNUmberBruteForce(a1);
        missingNUmberIfSorted(a1);
    }

    private static void missingNUmberBruteForce(final int[] a1) {

    }

    private static void missingNUmberIfSorted(final int[] a1) {

        for(int i=0;i< a1.length;i++)
        {
            if(a1[i]!=i+1)
            {
                System.out.print("missing number is ");
                System.out.println(i+1);
                break;
            }
        }
    }

    private static int findMissingNumber(final int[] a) {

        int i=0;
        while(i<a.length)
        {
            int correctIndex = a[i]-1;
            if(a[i]< a.length && a[i]!=a[correctIndex])
                swap(a,i,correctIndex);
            else
                i++;
        }
        System.out.println(Arrays.toString(a));
        //seacrh for the missing elemnt
        for(int j=0;j<a.length;j++)
        {
            int correctIndex = a[j]-1;
            if(a[j]!=a[correctIndex])
                return j+1;
        }
        return -1;
    }

    private static void swap(final int[] a,final int i ,final int correctIndex) {
        int temp=a[i];
        a[i]=a[correctIndex];
        a[correctIndex]=temp;
    }

}
