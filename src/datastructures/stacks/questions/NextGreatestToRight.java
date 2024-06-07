package datastructures.stacks.questions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class NextGreatestToRight {

    public static void main(String[] args) {

        int[] array = new int[]{1, 5,2,3,4};//[5,-1,3,4,-1]

        // 1-->3
        // 3-->4
        // 2-->4
        // 4-->-1


        // keep pushing into the stack
        // stack emty hai then -1 , top of a[i]> stack.top then push into array 


        findTheNextGreatestForEveryElement(array);



    }

    private static void findTheNextGreatestForEveryElement(final int[] array) {

        // universal logic for every elemnent in the array
        int[] result = new int[array.length];
       // {1, 5,2,3,4} ---> [5,-1,3,4,-1] -->[]
        Stack<Integer> stack = new Stack<>();
        for(int i= array.length-1;i>=0;i--){
            // Pop elements that are smaller than or equal to the current element
            while(!stack.isEmpty() && array[i]>= stack.peek())
            {
                stack.pop();
            }
            // if stack is empty
            if(stack.isEmpty())
                result[i]=-1;
            else {
                // The top element is the next greater element to the right
                result[i]=stack.peek();
            }
            // push the current element to the top of the stack
            stack.push(array[i]);
        }
        System.out.println(Arrays.toString(result));
    }
}
