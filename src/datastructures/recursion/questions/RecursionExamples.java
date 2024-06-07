package datastructures.recursion.questions;

public class RecursionExamples {

    // sum of digits
    // product of digits

    // 1234 1+2+3+4=1
    // 345 2*4*5
    // basic computatoion
    // number%10 gives the last number -- number /10 will give the number excluding the last

    public static void main(String[] args) {

        int n=345; // 12
        int sum = sumofDigits(n);
        System.out.println(sum);

        int product = productOfDigits(n);
        System.out.println(product);
    }

    private static int productOfDigits(final int n) {

        if(n%10==n)
        return n;


        int mod = n%10; // remainder or the last digit
        int numberLeft = n/10; // number which is leftover

        return mod*productOfDigits(numberLeft);
    }

    private static int sumofDigits(int n) {

        // using recursion
        if(n==0)
          return n;


        int mod = n%10; // remainder or the last digit
        int numberLeft = n/10; // number which is leftover

        return mod+sumofDigits(numberLeft);
    }
}
