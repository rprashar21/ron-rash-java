package datastructures.recursion;

public class RecursionSample {

    // rescursion is a method where the solution to a bigger problem depends upon smaller instances of the same problem
    // we have to define a base condition to avoid infinite loops
    // every problem can be solved using iteration or recursion
    // recursion is a function calling itself
    // uses space
    // space complexity is not constant because every function call takes up memory and they are recursive

    //
    public static void main(String[] args) {
        int n=5;
        int ans = fib(n);
        System.out.println("fibonacci sum is " +ans);

        // need to find a factorial
        int factorial = fact(n);
        System.out.println(factorial);
    }

    private static int fact(final int n) {

        if(n==1)
        return n;

        return n* fact(n-1);
    }

    private static int fib(int n) {
        // base condition
        if (n < 2)
            return n;

        return fib(n - 1) + fib(n - 2);

    }
}
