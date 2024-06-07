package datastructures.binary.question2d;

import java.util.Arrays;

public class SearchInSortedArray {

    public static void main(String[] args) {
//        int[][] arr = {
//                //  0  1  2   3
//                //  0 {10,20,30,40},
//                //   1 {11,25,35,45},
//                //   2 {28,29,37,49},  2,2
//                //   3 {33,34,38,50},
//        };
        int[][] arr = {

                {10, 20, 30, 40},
                {11, 25, 35, 45},
                {28, 29, 37, 49},
                {33, 34, 38, 50},
        };
        int target = 37;
        final int[] search = searchInMatrix(arr, target);
        System.out.println(Arrays.toString(search));
    }

    private static int[] searchInMatrix(final int[][] matrix, final int target) {
        int row = 0;
        int col = matrix.length - 1;

        while (row < matrix.length && col >= 0) {
            //case 1 when target ==
            if (target == matrix[row][col])
                return new int[]{row, col};

            if (target < matrix[row][col])
                col--;
                // case where target > mat[row][col]
            else
                row++;
        }
        return new int[]{-1, -1};
    }
}
