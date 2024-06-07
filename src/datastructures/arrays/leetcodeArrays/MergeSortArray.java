package datastructures.arrays.leetcodeArrays;

import java.util.Arrays;

public class MergeSortArray {

    //    nput: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//    Output: [1,2,2,3,5,6]
//    nums1 = [1], m = 1, nums2 = [], n = 0
    // Output: [1]
//    Input: nums1 = [0], m = 0, nums2 = [1], n = 1
//    Output: [1]
//    Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
//    The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
    public static void main(String[] args) {
//
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};

//        int[] a = {};
//        int[] b = {1};
        int m = 3;
        int n = 3;
        // merge(a, m, b, n);
        mergeInPlace(a, m, b, n);
    }

    private static void mergeInPlace(final int[] a, final int m, final int[] b, final int n) {

        int i = m - 1;
        int j = n - 1;
        int k = (m + n) - 1;

        while (i >= 0 && j >= 0) {
            if (a[i] > b[j]) {
                a[k] = a[i--];
            } else {
                a[k] =b[j--];
            }
            k--;
        }

        while (i >=0) {
            a[k--] = a[i--];
        }

        while (j >=0) {
            a[k--] = b[j--];
        }
        System.out.println(Arrays.toString(a));
    }

    public static void merge(int[] a, int m, int[] b, int n) {

        int i = 0;
        int j = 0;
        int[] result = new int[m + n];
        int k = 0;
        while (i < m && j < n) {
            if (a[i] < b[j])
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }
        // fill elements in array
        while (i < m) {
            result[k++] = a[i++];
        }

        while (j < n) {
            result[k++] = b[j++];
        }
        System.out.println(Arrays.toString(result));
    }
}


