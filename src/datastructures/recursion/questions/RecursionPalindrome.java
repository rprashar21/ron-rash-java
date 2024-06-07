package datastructures.recursion.questions;

public class RecursionPalindrome {

    public static void main(String[] args) {

        // palindrome number 12321

        // reverse a number check with original
        int n = 12321;
        int reverse = 0;
        int temp = n;
        while (n != 0) {

            int mod = n % 10;
            reverse = reverse * 10 + mod;
            n = n / 10;
        }


        int reversal = recursionPalindrome(temp);
        // this will be same as revrsal of number
        System.out.println(reversal);
        if (reversal == temp) {
            System.out.println("number are palindrome");
        } else {
            System.out.println("not palindrome");
        }
    }

    private static int recursionPalindrome(final int n) {
        int digits = (int) (Math.log10(n)) + 1;
        return helper(n, digits);
    }

    private static int helper(final int n, final int digits) {

        if (n % 10 == n)
            return n;

        int mod = n % 10;
        return mod * (int) (Math.pow(10, digits - 1)) + helper(n / 10, digits - 1);
    }
}
