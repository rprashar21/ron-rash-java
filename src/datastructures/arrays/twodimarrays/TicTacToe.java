package datastructures.arrays.twodimarrays;

import java.util.Scanner;

public class TicTacToe {

    public static String checkWinner(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0] + " wins";
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i] + " wins";
        }

        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0] + " wins";
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2] + " wins";

        return "No winner yet";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[3][3];

        System.out.println("Enter Tic Tac Toe board (3 rows of 3 characters: X, O, or space):");

        for (int i = 0; i < 3; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 3; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        System.out.println(checkWinner(board));
    }
}
