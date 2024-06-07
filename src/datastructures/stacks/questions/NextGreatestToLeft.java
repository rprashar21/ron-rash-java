package datastructures.stacks.questions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class NextGreatestToLeft {

    // input array [1,3,2,4]  --> find all elemnets greater in left side
    // output      [-1,-1,3,-1] -->

    // since this will be on2 we can reduce to o(n) using stacks to maintain the left side of the arrray

//    The Stack class represents a last-in-first-out (LIFO) stack of objects.
//    It extends class Vector with five operations that allow a vector to be treated as a stack.
//    The usual push and pop operations are provided, as well as a method to peek at the top item on the stack,
//    a method to test for whether the stack is empty, and a method to search the stack for an item and discover how far it is from the top.
//    When a stack is first created, it contains no items.
//    A more complete and consistent set of LIFO stack operations is provided by the SampleDeque interface and its implementations,
//    which should be used in preference to this class. For example:
//    SampleDeque<Integer> stack = new ArrayDeque<Integer>();

//    Stack Method
//    Equivalent SampleDeque Method
//    push(e) addFirst(e)
//    pop() removeFirst()
//    peek() peekFirst()

    public static void main(String[] args) {
        int[] array = new int[]{10, 3, 2, 4};//[-1,10,3,10]


        nextGreaterToLeft(array);
        nextGreaterToLeftNew(array);
    }

    private static void nextGreaterToLeftNew(final int[] a) {
        Stack<Integer> stack = new Stack<>();
        int output[] = new int[a.length];
        int k = 0;
        for (int i = 0; i < a.length; i++) {

            if (stack.isEmpty()) {
                output[k++] = -1;
            } else if (a[i] < stack.peek()) {
                output[k++] = stack.peek();
            } else if (a[i] > stack.peek()) {
                while (!stack.isEmpty() && a[i] > stack.peek()) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    output[k++] = -1;
                } else {
                    output[k++] = stack.peek();
                }
            }
            stack.push(a[i]);
        }
        System.out.println(Arrays.toString(output));
    }

    private static void nextGreaterToLeft(final int[] array) {
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();

        // stack is used to keep track of the left side of the array or the right side of the array

        for (final int j : array) {
            if (stack.isEmpty()) {
                list.add(-1);
            } else if (stack.peekFirst() > j) {
                list.add(stack.peekFirst());
            } else if (j > stack.peekFirst()) {
                // unless we find the greatest elemnt in the left side of a[i] current element we keep popping
                while (stack.size() > 0 && stack.peekFirst() < j) {
                    stack.removeFirst();
                }
                if (stack.isEmpty()) {
                    list.add(-1);
                } else {
                    list.add(stack.peekFirst());
                }
            }
            stack.addFirst(j);
        }
        System.out.println(list);
    }
}
