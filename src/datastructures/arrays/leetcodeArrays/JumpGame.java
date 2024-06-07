package datastructures.arrays.leetcodeArrays;

public class JumpGame {
    public static void main(String[] args) {
        int[] nums = new int[]{2,1,1,1,1,0};
        System.out.println( canJumpE(nums));
    }


    public static boolean canJumpE(int[] nums) {
        int maxReach = 0; // Initialize maxReach to 0
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                // If the current position is greater than the maximum reachable index, return false
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]); // Update maxReach
            if (maxReach >= nums.length - 1) {
                // If maxReach is greater than or equal to the last index, return true
                return true;
            }
        }
        return true; // If we've gone through the array without returning false, return true
    }
}
