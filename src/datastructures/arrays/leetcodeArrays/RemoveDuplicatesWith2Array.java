package datastructures.arrays.leetcodeArrays;

import java.util.Arrays;

public class RemoveDuplicatesWith2Array {

//    Given an integer array nums sorted in non-decreasing order,
//    remove some duplicates in-place such that each unique element appears at most twice.
//    The relative order of the elements should be kept the same.

    public static void main(String[] args) {
        int[] a = {1,1,1,2,2,3};
        int[] a1 = {0,0,1,1,1,1,2,3,3};

        inplaceRemoveDuplicateArray(a);
    }

    private static void inplaceRemoveDuplicateArray(final int[] a) {

        if(a.length == 0 || a.length ==1 || a.length ==2)
            throw new RuntimeException("Give an array of size more than 2");

        int i=2;
        int k=2;

        for(;i<a.length;i++)
        {
            if( a[i]!=a[k-2])
            {
                a[k++]=a[i];
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
