package datastructures.slidingWindow.fixed;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaximInSubArray {
    public static void main(String[] args) {

        //Algo
        /*
        * int i,j,sum=0
        * while(j<a.length)
        * {
        *   sum =sm+a[i]
        *
        *    if(j-i==k)
        *    sum = MAth.max(sum,maxSum)
        *
        *    slide the window and remove prev computation
        *    sum = sum -a[i];
        *    i++;
        *
        *  }
        *  j++;
        * */

        // find the max element is a subarray or window  of 3
        // this is a difficult problem
        int[] a = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        // so slide the window upto an elemnt of 3 and then get the maximum value from that

        // for better understanding
        //   https://youtu.be/xFJXtB5vSmM?list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj

        // maximum nos in window of size 3 are [10,-1,5,5,6,7]
        //
        int windowSize = 3;
        findMaximumInWindowSet(a, windowSize);
        findMaximumInWindowSet2(a,windowSize);
    }

    private static void findMaximumInWindowSet2(final int[] a,final int windowSize){
        //
        // take 2 pointer variable i and j and sum to some initial values
        // we will keeping adding the sum
        // when we have reached the window size ,, we will take the sum and
        // subtract or perform action to remove the prev value and add the new value of the window

        //complexity is 0(n)
        // take 2 pointer variable i and j and sum to some initial values
        int i = 0,j=0,sum=0;
        List<Integer> list = new ArrayList<>();
        while(j<a.length){
            // we will keeping adding the sum
            sum = sum +a[j];

            if(j-i+1==windowSize){
                // when we have reached the window size ,, we will take the sum till then and that to the list
             list.add(sum);
                // subtract or perform action to remove the prev value and add the new value of the window
             sum = sum -a[i];
             i++;
            }
            j++;
        }
        System.out.println(list
        );

    }

    private static void findMaximumInWindowSet(final int[] a, final int windowSize) {

        // algo
        int i = 0;
        int j = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        // calculation
        while (j < a.length) {
            // calculation to get the value

            while (!deque.isEmpty() && deque.getLast() < a[j]) {
                deque.removeLast();
            }
            deque.addLast(a[j]);

            // the window condition is met
            if (j - i + 1 == windowSize) {
                list.add(deque.peekFirst());

                // for sliding the window removing calculations on previous part
                if (a[i] == deque.peekFirst()) {
                    deque.removeFirst();
                }
                i++;
            }
            j++;
        }
        //
        System.out.println(list);
    }

}
