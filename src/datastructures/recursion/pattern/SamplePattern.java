package datastructures.recursion.pattern;

public class SamplePattern {
    // jsut find the row and then try to come up with a formula of how many cols are there in the row
    /*
     * *
     * * *
     * * * *
     * * *
     * *
     */
    // for above pattern see no fo rows 7 and find col in each row
    //

    public static void main(String[] args) {

      patternIncreasingAndThenDecreasing(4);
    }

    private static void patternIncreasingAndThenDecreasing(final int n) {

        for(int row =1;row<2*n;row++){
            // n = 7 the row will be rnning or having 7 rows
            // now fgure out the cols in each row
            int total = row < n ? row : 2*n-row;
            for(int col=1;col<=total;col++){
                System.out.print("x"+" ");
            }
            System.out.println();
        }

    }
}
