package datastructures.arrays.twodimarrays;

import java.util.Arrays;

public class SearchInStrictlySirtedMtarix {

    public static void main(String[] args) {
        // row is sorted and cloumn is also sorted
        //[] matrix sturcture
        // [
        //   10 20 30 40
        //   11 25 35 45
        //   28 29 37 49
        //   33 34 38 50
        // ]
        //[ {10,20,30,40}, {11 25 35 45},{28 29 37 49},{33 34 38 50}]
        //                                 0              1                  2                3
        //                           0   1  2  3    0   1   2  3     0  1   2   3          0   1    2   3
        int[][] array = new int[][]{{10,20,30,40},{50 ,60, 70, 80},{90, 100, 110, 120},{ 130, 140, 150, 160}};

        System.out.println(Arrays.toString(strictlySorted(array,100)));
        System.out.println(Arrays.toString(strictlySorted(array,140)));
        System.out.println(Arrays.toString(strictlySorted(array,60)));
        System.out.println(Arrays.toString(strictlySorted(array,600)));
    }

    private static int[] strictlySorted(final int[][] array, final int target) {

        int row =0;
        int col=array[row].length-1; // 3
        while(row<array.length)
        if(target<=array[row][col])
        {
            // binary search
           return new int[] {row, binarySearch(array[row],target)};
        }
        else
        {
            row++;
        }
        return new int[]{-1,-1};
    }

    private static int binarySearch(final int[] array, final int target) {

        int start = 0;
        int end = array.length-1;

        while(start<=end){
            int mid = start +(end - start )/2;

            if(target == array[mid])
                return mid;
            else if (target>array[mid]) {
                start = mid + 1;
            }
            else{
                end = mid-1;
                }
            }
        return -1;
        }
    }

