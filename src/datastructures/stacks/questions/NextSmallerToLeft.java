package datastructures.stacks.questions;


import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextSmallerToLeft {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 2, 4};// [-1,1,1,2]

        nextSmallerToLeft(a);


    }

    private static void nextSmallerToLeft(final int[] a) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] output = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            while (!stack.isEmpty() && a[i] <= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                output[i] = -1;
            } else {
                output[i] = stack.peek();
            }
            stack.push(a[i]);
        }
        out.println(Arrays.toString(output));
    }
}
