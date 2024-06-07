package datastructures.recursion.questions;

public class RecursionReversal {

    // reverse a number using recursion

    public static void main(String[] args) {

        int n =45678; // 87654

        // this is using strings
        int reversal = reversal(n);

        // without strings
        int reversal1 = actulaReversal(n);
        System.out.println(reversal1);
    }

    private static int actulaReversal(final int n) {
        int digits = (int)(Math.log10(n))+1; //

        // need a helper function
        return helper(n,digits);
    }

    private static int helper(final int n, final int digits) {
        // base condition
        if(n%10==n)
            return n;


        int rem = n%10;
        // check the whiteboard explanation for this
        return rem * (int)(Math.pow(10,digits-1) )+ helper(n/10,digits-1);
    }

    private static int reversal(final int n) {

        if(n%10==n)
            return n;

        int mod =n%10;
        int number =n/10;

        return Integer.parseInt(mod +String.valueOf(reversal(number)));
    }
}
