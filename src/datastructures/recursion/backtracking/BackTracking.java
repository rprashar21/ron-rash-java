package datastructures.recursion.backtracking;

public class BackTracking {
    //Also called as Depth first search
    /*
    BAcktracking mean going back to the previous state if the actual one is not feasible
    * 1. for every node we check whether the given node can be a valid solution
    *   every node is a state
    *
    * 2. if not then the whole subtree is skipped
    *
    * 3. it recursively travles all subtrees of the nodes
    *
    N queens problem -- means we have to place queens in the chess where they cannot attack each other
    * */
    public static void main(String[] args) {
        // solve this problem for 4*4 matrix or chess board
        NQueens nQueens = new NQueens(4);
        nQueens.solve();
    }

}

class NQueens {
    private int[][] array;
    private int numberOfQueens;


    NQueens(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
        this.array = new int[numberOfQueens][numberOfQueens]; // no of columns and rows are equal to the number of queens
    }

    //
    public void solve() {
        // start solving the problem with the startingIndex 0f 0 that us the first column
        if (setQueens(0)) {// if this is true then we have a solution to the problem
            printQueens();
        } else {
            System.out.println("There is no solution");
        }
    }

    private boolean setQueens(int colIndex) {

        if (colIndex == numberOfQueens) {
            // when we have reached lastcolumn and we have placed successfully the queens in their
            // right order we have reachged the base condition
            return true;
        }
        // consider all the apossible locations in the given column one by one
        for (int row = 0; row < numberOfQueens; row++) {
            // if the current place is valid for the queen
            if (isValid(row, colIndex)) {
                // the location is valid(we place the queen at that place )
                this.array[row][colIndex] = 1;
                // we the look in the next colum to place the queen
                if (setQueens(colIndex + 1)) {
                    return true;
                }

                // backtrack --> at this position we had placed the queen i.e we had made it to 1 now we have to make it to o again
                this.array[row][colIndex] = 1;
            }
        }
        // this is that we have considered all the positions in that column and found no success
        return false;
    }

    private boolean isValid(int rowIndex, int colIndex) {
        // check if the queen is placed at the current index
        // the queens cannot be the same row
        for (int i = 0; i < colIndex; i++) {
            if (array[rowIndex][i] == 1)
                return false;
        }
        // check the dianols
        // diagnol locations are from top left to bottom right
        for (int i = rowIndex, j = colIndex; i >= 0 && j >= 0; i--, j--) {
            if (array[i][j] == 1)
                return false;
        }
        // diagnol locations are from top right to bottom left
        for (int i = rowIndex, j = colIndex; i<array.length && j >= 0; i++, j-- ) {
            if (array[i][j] == 1)
                return false;
        }
        return true;
    }

    private void printQueens() {
        // print the 2d array where 1 represent the queen place
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1)
                    System.out.print(" * ");
                else
                    System.out.print(" - ");
            }
            System.out.println("");
        }
    }


}