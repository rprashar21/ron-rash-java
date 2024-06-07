package datastructures.hashmaps.hashmapQuestions;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
//    A happy number is a number defined by the following process:
//
//    Starting with any positive integer, replace the number by the sum of the squares of its digits.
//    Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
//    Those numbers for which this process ends in 1 are happy.
//    Return true if n is a happy number, and false if not.

    public static void main(String[] args) {
        int n = 19;

        System.out.println(findHappyNUmber(n));

    }

    private static boolean findHappyNUmber( int n) {

        Set<Integer> set = new HashSet<>();
        while(n!=1 && !set.contains(n))
        {
            set.add(n);
            n=checkForNumber(n);
        }
       return n==1;
    }

    private static int checkForNumber( int n) {

        int sum=0;
        while(n>0)
        {
            int mod = n%10;
            sum = mod*mod+sum;
            n=n/10;
        }
        return sum;
    }
}
