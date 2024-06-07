package datastructures.sorting.divideAndConquer;

import java.util.Arrays;

public class QuickSort {

    // divide and conquer algorithm divides the problem into smalller and smaller problems
    // it is an efficient algorrthm for O(Nlogn) wortst case is on2
   // in place algorithm
    // comparison based alogrithm
    // in place algorithm but not stable
    // for primitive types (ints ,floats ) quickSort is used
    // for reference types merge sort is used

    // QuickSort has 2 phases Partition Phase and Recursion Phase
    // algo genertaes the pivot
    // elements to the left are smaller and right are bigger

    // Recusrion phase we keep doing this

    // How to get the pivot 2 main approaches
    // 1. use the middle element of the array
    // 2  we can generate a random number

    /*
    *  Algorithm for quicksort is
    *
    *
    * */
    public   static int count =0;

    public static void main(String[] args) {
        int [] array =new int[]{5, 3, 8, 4, 2, 7, 1, 6};

        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(final int[] array, final int left, final int right) {

        if(left < right)  // base condition so that it does not go into infinite loop
        {
            // 2 phases PARTITION AND RECURSION
            int pivot = partition2(array,left,right); // left to pivot are smaller ,, right to pivot are greater
            quickSort(array,left,pivot-1);
            quickSort(array,pivot+1,right);

        }

    }

    private static int partition2( int[] array,int left, int right) {
     // {}      pivot =3
     //  i      j
      // selec the rightmost elemet  as pivot
     int pivot = array[right];
     // partionIndex
     int partitonIndex=left; // 2 pointers

     // now will start comparing from starting of the list with the pivot
     // elements which are smaller than pivot will be swapped

     for(int i=left;i<right;i++)
     {
         if(array[i]<=pivot)
         {
             //swap
             int temp=array[i];
             array[i]=array[partitonIndex];
             array[partitonIndex]=temp;
             partitonIndex++;
         }
     }
     //finall we have to swap pindex and pivot

        int temp = array[partitonIndex];
       array[partitonIndex]=array[right];
       array[right]=temp;

        System.out.println("iteration "+ ++count+  " "+Arrays.toString(array));
        System.out.println("index  "+partitonIndex);
       return partitonIndex;
    }


    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // choose the rightmost element as the pivot
        int i = left - 1; // pointer for tracking elements smaller than the pivot

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++; // increment the pointer
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1; // return the pivot's final index
    }



}
