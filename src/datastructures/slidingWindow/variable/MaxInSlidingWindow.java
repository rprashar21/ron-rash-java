package datastructures.slidingWindow.variable;

import java.util.*;

public class MaxInSlidingWindow {

    public static int[] findMaxInWindow(int[] nums, int k) {
        if (nums == null || k <= 0 || nums.length < k) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>(); // stores indices

        for (int i = 0; i < n; i++) {

            // Remove elements out of this window (from the front)
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove all smaller elements than current from the back
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);

            // Start adding to result once the first window is ready
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        int windowSize = 3;
        System.out.println(Arrays.toString(findMaxInWindow(input, windowSize)));
        // Output: [3, 3, 5, 5, 6, 7]
    }
}

