package datastructures.sorting.divideAndConquer;

import java.util.Arrays;

public class MergeSortWithCopyArray {
    public static void main(String[] args) {
        int[] array = new int[]{10, 2, 4, 5, 3, 7, 6};

        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void mergeSort(final int[] array, final int left, final int right) {

        // base condtion -- because we are using recursion

        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, final int left, final int mid, final int right) {

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                array[k++] = array[i++];
            } else {
                array[k++] = array[j++];
            }
        }
        // one of the left or right array has been exhausted
        while (i <= mid) {
            array = Arrays.copyOfRange(array, k, mid);
        }
        while (j <= right) {
            array = Arrays.copyOfRange(array, k, right);
        }
    }
}
