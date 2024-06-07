package datastructures.recursion.questions;

public class RecursionSampleo2 {
    // find sum of n numbers

    public static void main(String[] args) {
        int n=5;// 5+4+2+3+1; 15
        int ans = sum(5);
        System.out.println(ans);

        // find the sum of the digits
        int sum=sumofdigits(1342); // 1+3+4+2;
        System.out.println(sum);
    }

    private static int sumofdigits(final int n) {
        if(n==0)
            return n;

        int left = n/10;
        int mod = n%10;
        return mod +sumofdigits(left);
    }

    private static int sum(final int n) {

        if(n==1)
            return n;

       return n+sum(n-1);
    }


}
