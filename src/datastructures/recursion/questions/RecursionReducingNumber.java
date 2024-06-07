package datastructures.recursion.questions;

public class RecursionReducingNumber {

    // Recursion to reduce the number to 0 and then counting the steps taken to do that
    // eg 14 -- if even divide by 2 ,, odd then subtract by 1
    /*
     * step 1  14/2 = 7
     * step 2  7-1 =  6
     * step 3  6/2 = 3
     * step 4  3-1 = 2
     * step 5  2/2 = 1
     * step 6 = 1-1 = 0
     *
     *  total no of steps = 6
     * */

    public static void main(String[] args) {

        int n = 14;
        int counter = 0;
        int countSteps = countSteps(n, counter);
        System.out.println(countSteps);
    }

    private static int countSteps(int n, int counter) {

        if (n == 0)
            return counter;
        if (n % 2 == 0)
            return countSteps(n / 2, ++counter);

        return countSteps(n - 1, ++counter);

    }
}
