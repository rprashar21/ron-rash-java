package datastructures.stacks.questions;

import java.util.Arrays;
import java.util.Stack;

public class DailTemperatures {

    public static void main(String[] args) {
//        The "Daily Temperatures" problem asks you to find, for each day in an input array of daily temperatures, how many days you have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
//
//                For example, given a list of daily temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

        int[] a = {73, 74, 75, 71, 69, 72, 76, 73};//[0,8,1,5,4,2,1,1,0,0] --> [8,1,5,4,3,2,1,1,0,0]
        dailyTemperatures(a);
        // this is agood solution without stack
        dailyTemPractice(a);

        practiceDailyTemperature(a);
    }

    public static int[] dailyTemperatures(int[] a) {

        int[] result = new int[a.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < a.length; i++) {
            while (!stack.isEmpty() && a[i] > a[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index; // Calculate the difference between the current day and the day from the stack
            }
            stack.push(i); // Push the current day's index onto the stack
        }
        System.out.println(Arrays.toString(result));
        return result;

    }

    public static int[] dailyTemPractice(int[] a) {
        //output array
        int result[] = new int[a.length];

        int i = 0;
        int j = 1;
        int k = 0;

        while (i <= a.length - 2) {

            if (j > a.length - 1) {
                result[k++] = 0;
                i++;
                j = i + 1;
            } else if (a[i] < a[j]) {
                result[k++] = j - i;
                i++;
                j = i + 1;
            } else {
                j++;
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static int[] practiceDailyTemperature(int[] a) {
        //output array
        int result[] = new int[a.length];


        int k = 0;
        int j;
        for (int i = 0; i <= a.length - 2; i++) {
            j = i + 1;
            while (j < a.length && a[i] >= a[j] ) {
                j++;
            }
            if(j<a.length)
                result[k++] = j - i;
        }


        System.out.println(Arrays.toString(result));
        return result;
    }
}

class Pair1 {
    public int key;
    public int value;

    public Pair1(int key, int value) {
        this.key = key;
        this.value = value;
    }


}

