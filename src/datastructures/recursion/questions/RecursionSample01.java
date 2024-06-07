package datastructures.recursion.questions;

public class RecursionSample01 {
    // print numbers
    /*
     * Recursion is basically done with dynamic programming to reduce the duplicate work
     *
     * In Recursion 3 things are important
     *  1. Arguments to pass  here its n which will change
     *  2. return type. every function call will just print the value
     *  3. what argument should go in the body
     * */

    public static void main(String[] args) {
        int n = 5; // 5,4,3,2,1,
        printf(n);
        System.out.println("");
        printfNew(n); // 1,2,3,4,5,---

        // combine both in one
        System.out.println("combing both");
        prinftBoth(n);


    }

    private static void prinftBoth(final int n) {
        if (n == 0) {

            System.out.print(",");
            return;
        }


        System.out.print(n + " ");
        prinftBoth(n - 1);
        System.out.print(n + " ");
    }

    private static void printfNew(final int n) {
        if (n == 0) {
            return;
        }


        printfNew(n - 1);
        System.out.print(n + " ");

    }

    private static void printf(final int n) {

        // base condition
        if (n == 0)
            return;
        System.out.print(n + " ");
        printf(n - 1);

    }
}
