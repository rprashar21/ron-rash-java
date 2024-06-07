package datastructures.arrays.arrayQuestionsleetcode;

import java.util.Arrays;

public class FindMissingNumberInArrayCuyclicSort {
    public static void main(String[] args) {

        // array is sorted and elements in correct position will the value - 1
        // so we need t find the correct index

        int[] a = new int[]{5, 2, 4, 1}; // the correct index of every  element in such type of array  is the value -1

//        int missingNmber = findMissingNmber(a);
//        System.out.println(missingNmber);
        FindMissingNUmber(a);
    }

    private static int findMissingNmber(final int[] a) {

        // first do a sort
        // then find the missing number

        int i = 0;
        while (i < a.length) {
            int correctIndex = a[i] - 1;
            if (a[i]<=a.length && a[i] != a[correctIndex]) {
                // swap
                int temp = a[i];
                a[i] = a[correctIndex];
                a[correctIndex] = temp;
            } else {
                i++;
            }
        }
        // here i will get the the sorted array [1,2,4,5]
        // the element which is not at its position is the missing number
        System.out.println(Arrays.toString(a));
        for (int j = 0; j < a.length; j++) {
            if (a[j] != j+1) {
                return j +1;
            }
        }
        return -1;
    }

    private static void FindMissingNUmber(final int[] a){

        // step1 loop tthru the elements and
           int i=0;
           while(i< a.length) {
               int correctCurrentIndex = a[i] - 1;
               if (a[i] <= a.length && a[i] != a[correctCurrentIndex]) {
                   //swap the current index with its correct index
                   int temp = a[i];
                   a[i] = a[correctCurrentIndex];
                   a[correctCurrentIndex] = temp;

               }
               else {
                   i++;
               }
           }
        System.out.println(Arrays.toString(a));

           for(int j=0;j<a.length;j++){
             if(j!=a[j]-1)
             {
                 System.out.println("missing number is "+(j+1));
             }
           }
    }
}
