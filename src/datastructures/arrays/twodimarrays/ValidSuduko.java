package datastructures.arrays.twodimarrays;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ValidSuduko {

    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        boolean isValid = isValidSudoku(board);
        boolean isValidSuduko = simpleMethod(board);
        System.out.println("sudo isValid " + isValidSuduko);
        System.out.println("Is the Sudoku board valid? " + isValid);
    }

    private static boolean simpleMethod(final int[][] array) {
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                int num = array[i][j];
                if (num != 0) {
                    // is it present the row col or box set
                    if (!set.add(num + " at row " + i) ||
                            !set.add(num + " at col " + j) ||
                            !set.add(num + " at box " + i / 3 + "-" + j / 3)
                    ) {
                        return false;
                    }
                }
            }
        }
        System.out.println(set);
        return true;
    }

    public static boolean isValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            Set<Integer> boxSet = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                // Validate row


                // rowSet
                if (board[i][j] != 0 && !rowSet.add(board[i][j]))
                    return false;

                //columnset
                if (board[j][i] != 0 && !colSet.add(board[j][i]))
                    return false;

                if(i==4 && j== 4)
                {
                    System.out.println("hello");
                }
                // Validate box
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                if (board[rowIndex + j / 3][colIndex + j % 3] != 0 && !boxSet.add(board[rowIndex + j / 3][colIndex + j % 3])) {
                    return false;
                }


            }
            System.out.println(boxSet);
        }
        return true; // If all checks pass without returning false
    }


}
