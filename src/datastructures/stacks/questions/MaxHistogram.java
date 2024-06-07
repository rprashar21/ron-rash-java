package datastructures.stacks.questions;

import java.util.Arrays;
import java.util.Stack;

public class MaxHistogram {
//    Given an array of integers heights representing the
//        histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

    //Input: heights = [2,1,5,6,2,3]
    //Output: 10
    //Explanation: The above is a histogram where width of each bar is 1.
    //The largest rectangle is shown in the red area, which has an area = 10 units.
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] leftSmaller = new int[n]; // Next smaller element to the left
        int[] rightSmaller = new int[n]; // Next smaller element to the right
        Stack<Integer> stack = new Stack<>();

        // Fill leftSmaller array
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            leftSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        System.out.println("left Smaller");
        System.out.println(Arrays.toString(leftSmaller));



        stack.clear(); // Clear the stack to reuse it

        // Fill rightSmaller array
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            rightSmaller[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        System.out.println("right Smaller");
        System.out.println(Arrays.toString(rightSmaller));

        // Calculate the maximum area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = rightSmaller[i] - leftSmaller[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + largestRectangleArea(heights));
    }
}
