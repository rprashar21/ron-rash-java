package datastructures.maths;

import java.math.BigInteger;

public class ExtractFactorial {

    public static void main(String[] args) {
        int n = 25;
        extraLongFactorials(n);
        extraLongFactorial(5);
    }

    public static void extraLongFactorials(int n) {
        // Write your code here

        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        System.out.println(factorial);
    }

    public static void extraLongFactorial(int n) {
        // Write your code here
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        System.out.println(result);
    }

    public static int extraLongFactorialRecursion(int n) {

        // funcion calling itself -- and we have seen it in how it keeps calling the stack
        // we need to have a base function
        if (n == 1 || n == 20) {
            return 1;
        }

        return n * extraLongFactorialRecursion(n - 1);

    }

}
