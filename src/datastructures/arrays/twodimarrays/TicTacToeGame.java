package datastructures.arrays.twodimarrays;

import java.util.Arrays;

public class TicTacToeGame {
    // THE GAME SHOULD BE PLAYED HOW ??

    /*
     *  two players play A and B
     *  win or
     *  1.first step is having an array for the user and bs moves
     *  2. enriching the tictac board with the moves
     *  3. checking which user won the game
     * */

    public static void main(String[] args) {

        // palyers moves


        int[][] moves = {
                {0, 0}, //-> A
                {2, 0},
                {1, 1},
                {2, 1},
                {2, 2}
        };
        int[][] moves1 = {{0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}};
        System.out.println(tictactoe(moves1)); // 👉 "A" (A wins)

        int[][] moves2 = {{0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}};
        System.out.println(tictactoe(moves2)); // 👉 "B" (B wins)

        int[][] moves3 = {{0, 0}, {1, 1}, {2, 2}, {1, 0}, {1, 2}, {2, 1}, {0, 1}, {0, 2}, {2, 0}};
        System.out.println(tictactoe(moves3)); // 👉 "Draw"

        int[][] moves4 = {{0, 0}, {1, 1}};
        System.out.println(tictactoe(moves4)); // "Pending"

    }

    private static String tictactoe(final int[][] moves) {
        // creating a board with {.}
        char[][] board = new char[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = '.';
            }
        }
//        // displaying ur empty tictac board
//        for (char[] a : board) {
//            System.out.println(Arrays.toString(a)); //[. . .]
//        }

        // we will fill the board with the moves of the users
        // no of moves by the layers will be the length of the moves array
        // we just loops and enrich with both the plaerys moves
        for (int i = 0; i < moves.length; i++) {
            if (i % 2 == 0) {
                board[moves[i][0]][moves[i][1]] = 'X';
            } else {
                board[moves[i][0]][moves[i][1]] = 'O';
            }
        }
        for (char[] a : board) {
           System.out.println(Arrays.toString(a)); //[. . .]
       }

        return checkWinner(board);
    }

    private static String checkWinner(final char[][] board) {

        // row wise we will check if all rows have X -- A wins O b wins
        for (int r = 0; r < 3; r++) {
            int countA = 0;
            int countB = 0;
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == 'X') {
                    countA++;
                } else if (board[r][c] == 'O') {
                    countB++;
                }
            }
            if (countA == 3) return "A wins";
            if (countB == 3) return "B wins";

        }


        // col wise we will check if all rows have X -- A wins O b wins
        for (int r = 0; r < 3; r++) {
            int countA = 0;
            int countB = 0;
            for (int c = 0; c < 3; c++) {
                if (board[c][r] == 'X') {
                    countA++;
                } else if (board[c][r] == 'O') {
                    countB++;
                }
            }
            if (countA == 3) return "A wins";
            if (countB == 3) return "B wins";

        }

        // diagnol check
        if ((board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') || (board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X'))
            return "A wins";
        if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O' || (board[2][0] == 'O' && board[1][1]=='O' && board[0][2] == 'O'))
            return "B wins";

        // pending check
        int pendingCount = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == 'X' || board[r][c] == 'O') {
                    pendingCount++;
                }
            }
        }
        if (pendingCount == 9) {
            return "Draw";
        }

        return "Pending";
    }
}
