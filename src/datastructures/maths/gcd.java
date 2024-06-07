package datastructures.maths;

public class gcd {
    // gcd means greatest comon divisor 0f 2 numbers
    // gcd is the greatest positive intger which divides both the numbers
    // for eg factors of 24 and 12
    // factors means number divide by an integer and remainder is 0
    // factors of 24 are -- 1,2,3,4,6,8,12,24
    // factores of 12 are ==1,2,3,4,6,12
    // now gcd is for the 2 number is 12

    // gcd can be found using euclidean algo which is a recursvice method or thru iteration

    // what is euclidean algo -- it says that the greatest common divisor for 2 numbers does not change when we replace the larger number
    // the diffferemce of the 2 number or by modulo of nubers
    public static void main(String[] args) {

        int a = 24;
        int b = 9;
        int gcd;
        if (a > b) {
            gcd = gcd_recursion(a, b);
        } else {
            gcd = gcd_recursion(b, a);
        }
        System.out.println(gcd);

        System.out.println(gcd_Iteration(a,b));
    }

    private static int gcd_recursion(int a, int b) {
        // assuming a >=b if not do it in the method calling this

        // base condtion if b divides a and no remainder then the smaller number is gcd
        if (a % b == 0) {
            return b;
        }
        return gcd_recursion(b, a % b);
    }

    private static int gcd_Iteration(int a, int b) {
      int temp=0;
      while(b!=0)
      {
          temp=b;
          b=a%b;
          a=temp;
      }
      return temp;
    }


}
