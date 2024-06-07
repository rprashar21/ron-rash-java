package datastructures.arrays.leetcodeArrays;

public class MinJumps {
    public static void main(String[] args) {
//        Input: nums = [2,3,0,1,4]
//        Output: 2
//        Explanation: The minimum number of jumps to reach the last index is 2.
//        Jump 1 step from index 0 to 1, then 3 steps to the last index.
//    }

    //   int[] a = {3, 0, 1, 1, 1, 1};
        int[] a = {2, 3, 1, 1, 4};
       minJump(a);
    }

    private static int minJump(final int[] a) {

        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < a.length - 1; i++) {
            // Update the farthest we can reach from current i
            curFarthest = Math.max(curFarthest, i + a[i]);

            // If we have reached the end of the current jump,
            // it's time to jump again (except when we're already at the last element)
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;

                // If current farthest is already at or beyond the last index,
                // no need to continue, as we can reach the end
                if (curFarthest >= a.length - 1) break;
            }
        }
        System.out.println(jumps);
        return jumps;
    }
}
