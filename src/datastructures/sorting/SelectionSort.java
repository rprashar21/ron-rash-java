package datastructures.sorting;

import java.util.Arrays;

public class SelectionSort {
    // performs well in small list
    /*
     * Algorithm where we find the max elemeet in the array and then swap that with the last element
     * then we reduce the range ie we ignore the last element and so forth
     *
     *  very similar to buble sort -- where we find the max element
     *
     *  Algo
     *  Outer loop till the end of the array 0----->a.length
     *  keep lastIndexValue --- a.length-i-1;
     *  find the index of the maxValue in the array and then swap it with the lastIndex Value
     *  Once this is done
     *
     * */

    public static void main(String[] args) {
//        int[] array = {4, 5, 1, 2, 3};
//        selectionSort(array);
//
//        System.out.println(Arrays.toString(array));

        //ist swap {4,3,1,2,5} i=0 last =4  maxelem = 5 range is 0-4 {4,3,1,2,5}
        // 2nd swap {2,3,1,4,5} i=1 last=3 maxelem = 4  range is 0-3 {2,3,1,4},5
        //3rd swap {2,1,3,4,5}  i=2 last=2 maxelem= 3    range is 0-2 {2,1,3},4,5
        //4th swap {1,2,3,4,5} i=3 last =1 maxelem =2     range is 0-1{2,1} 3,4,5 after swap
        // 5th swap {1,2,3,4,5}

        selectionSortNew();
    }

    private static void selectionSort(final int[] array) {
        //
        for (int i = 0; i < array.length; i++) {
            // find the range
            int last = array.length - i - 1;
            // find the index of the maximum element
            final int maxIndex = maxIndex(array, 0, last);

            // swap the maxIndex element with the last element
            swap(array,maxIndex,last);
            System.out.println("Swappping after iteration : "+i + " "+Arrays.toString(array));
        }

    }

    private static void swap(final int[] array, final int maxIndex, final int last) {
        int temp =array[maxIndex];
        array[maxIndex]=array[last];
        array[last]=temp;

    }

    private static int maxIndex(final int[] array, final int start, final int last) {
        // find the index of the largest element
        int max = array[start];
        int maxIndex = start;
        for (int i = start; i <= last; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }

        }
        return maxIndex;
    }


   private static void  selectionSortNew(){

       int[] a = {4, 5, 1, 2, 3};

       System.out.println(Arrays.toString(a));
       for(int i=0;i< a.length;i++){
           // this will keep excluding he last element from the array as
           int lastIndex = a.length-i-1;
           // code to find the indexOfTheMaxElement
           final int indexOfTheMaxElement = indexOfTheMaxElement(a, lastIndex);

           // swap the value of indexOfTheMaxElement with value of lastIndex of array--
           int temp =a[indexOfTheMaxElement];
           a[indexOfTheMaxElement]=a[lastIndex];
           a[lastIndex]=temp;

           System.out.println("after iteration "+i+" "+Arrays.toString(a));
        ;
       }
   }

    private static int indexOfTheMaxElement(final int[] a, int lastIndex) {

        int maxIndex = 0;
        int maxValue =Integer.MIN_VALUE;
        for(int i =0; i <=lastIndex; i++){

            if(a[i]>maxValue)
            {
                maxValue=a[i];
                maxIndex= i;
            }
        }
        return maxIndex;
    }
}
