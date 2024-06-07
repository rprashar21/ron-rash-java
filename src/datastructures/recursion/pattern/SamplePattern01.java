package datastructures.recursion.pattern;

public class SamplePattern01 {


    // why basic idea of patterns
    // the outer for loop is for the number of rows
    // the inner for looop is for the number of cols

    /*
          *
          *  *
          *  *  *
          *  *  * *
         here the row1 = no of cols =1
                 row 2 = no of cols =2

    *
     */
    public static void main(String[] args) {

       basicPattern(5);
        System.out.println("Reverse the pattern");
        basicPatternReverse(5);
        
       printNumber(5) ;


    }

    private static void printNumber(final int n) {
        // n is basically no of rows we should have
        for(int row=1;row<=n;row++)
        {
            for(int col=1;col<=row;col++)
            {
                System.out.print(col+" ");
            }
            System.out.println("");
        }
    }

    private static void basicPatternReverse( int n) {
        // ist row will have n
        for(int row=1;row<=n;row++){
            for(int col=1;col<=n-row+1;col++)
            {
                System.out.print("*"+" ");
            }
            System.out.println();
        }
    }

    private static void basicPattern(final int n) {

        for(int row=1;row<=n;row++){
            for(int col=1;col<=row;col++)
            {
                System.out.print("*"+" ");
            }
            System.out.println();
        }
    }
}
