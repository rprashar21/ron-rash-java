package datastructures.greedy;

public class MaxSubArrayPart2 {

    public static int[] findMaxSubarray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0;
        int end = 0;
        int currentStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                currentStart = i;
            } else {
                currentSum = currentSum + nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = currentStart;
                end = i;
            }
        }

        System.out.println("start: "+start);
        System.out.println("end: "+end);
        System.out.println("currentStart: "+currentStart);


        // Extract the maximum subarray from the original array.
        int[] maxSubarray = new int[end - start + 1];
        for (int i = start, j = 0; i <= end; i++, j++) {
            maxSubarray[j] = nums[i];
        }

        return maxSubarray;
    }

    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int[] maxSubarray = findMaxSubarray(nums);

        System.out.println("Maximum subarray is:");
        for (int num : maxSubarray) {
            System.out.print(num + " ");
        }
    }
}
