package datastructures.sorting.divideAndConquer;

import java.util.Arrays;

public class MergeSort {

    // divide and Conquer --
    // Merge Sort O(nlogn) in worst case

    public static void main(String[] args) {
        int [] array =new int[]{5, 3, 8, 4, 2, 6, 1, 9};

        mergeSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));

        //[5, 3, 8, 4, 2, 7, 1, 10]
        // [5,3,8,4] [2,7,1,10]
        //  [5,3] [8,4]  [2,7] [1,10]
         //   [5] [3] [8] [4]
    }

    private static void mergeSort(final int[] array, final int left, final int right) {

        if(left<right)
        {
            // find the midlle element
            int middle = left + (right -left)/2;
            mergeSort(array,left,middle);
            mergeSort(array,middle+1,right);
            merge(array,left ,middle,right);
        }

    }

    private static void merge(int[] array, final int left, final int middle, final int right) {

        //[5, 3, 8, 4, 2, 7, 1, 10]       [5,3,8,4] [2,7,1,10]
        //left      mid
        int[] temp = new int[array.length];
         int i =left; // pointer for left array
         int j = middle+1; // pointer for right array
         int k = left;  // pointer for temporary array

        while(i<=middle && j<=right)
        {
            if(array[i]<=array[j])
            {
                temp[k]=array[i];
                k++;
                i++;
            }
            else{
                temp[k]=array[j];
                k++;
                j++;
            }
        }

        // when the above while ends it means one of the left or right array is exhausted
      while(i<=middle)
      {
          temp[k++]=array[i++];
      }
        while(j<=right)
        {
            temp[k++]=array[j++];
        }

      // copy from temp to main array
        for (k = left; k <= right; k++) {
            array[k] = temp[k];
        }
    }
}
