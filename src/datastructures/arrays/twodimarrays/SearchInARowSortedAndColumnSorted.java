package datastructures.arrays.twodimarrays;

import java.util.Arrays;

public class SearchInARowSortedAndColumnSorted {
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
                        //         0              1                  2                3
                       //         0   1  2  3    0   1   2  3     0  1   2   3     0   1    2  3
     int[][] array = new int[][]{{10,20,30,40},{11 ,25, 35, 45},{28, 29, 37, 49},{ 33, 34, 38, 50}};

        System.out.println(Arrays.toString(searchInSortedRowAndCol(array,29)));
        System.out.println(Arrays.toString(searchInSortedRowAndCol(array,50)));
        System.out.println(Arrays.toString(searchInSortedRowAndCol(array,11)));
        System.out.println(Arrays.toString(searchBYBinaryMetthod(array,38))); // [3,2]
    }

    private static int[] searchBYBinaryMetthod(final int[][] array, final int target) {

        int [] result = new int[2];
        int rowIndex =-1;
        for(int[] a : array)
        {
            rowIndex= rowIndex+1;
            int start =0;
            int end = a.length-1;
            result = binarySearchMethod(a, start, end, target,rowIndex);
            if(result[0] != -1)
                return result;
        }
        return result;
    }

    private static int[] binarySearchMethod(final int[] a,  int start,  int end, final int target,final int rowIndex) {
        int mid = start + (end-start)/2;
         while(start<end)
         {
             if(a[mid]==target)
                 return new int[]{rowIndex,mid};
             else if(target>a[mid])
                 start = mid+1;
             else
                 end =mid-1;
         }
         return new int[]{-1,-1};
    }

    private static int[] searchInSortedRowAndCol(final int[][] array,final int target) {

        int row = 0;
        int col = array[row].length-1; // col =3

       while(row<array.length)
       {
           if(target==array[row][col])
           {
               return new int[]{row,col};
           } else if (target > array[row][col]) {
               row++;
           } else if (target<array[row][col]) {
               col--;
           }
       }

        return new int[]{-1,-1};

    }
}
