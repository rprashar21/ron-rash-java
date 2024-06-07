package datastructures.slidingWindow.fixed;

public class MaxSubarray1 {

    /*
     * array is given -- window size is given
     * find max/min from subarray --
     * whenever we have to work with some kind of sub-array we go for sliding window
     *      2 types of sliding window -- one with fixed another with variable size
     *      fixed -- window size is given // varaiable no window size is given
     * */
    public static void main(String[] args) {

        //Question -- find max in subarray of size 3
        int[] a = {2, 5, 1, -2};  // 2+5+1-- 8   // 5+1+8=14 ,,   1+8+2=11 ,, 8+2+9=19 //2+9+1=12
        int windowSize = 3;

        System.out.println(slidingWindowMaxSubArray(a, windowSize));
    }

    private static int slidingWindowMaxSubArray(final int[] a, final int k) {

        //2 pointers i and j ---
        // i --startWindow j --endWindow
        int j = 0;
        int i = 0;
        int maxValue = Integer.MIN_VALUE;
        int sum = 0;
        while (j < a.length - 1) {
            sum += a[j];
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                maxValue = Math.max(sum, maxValue);
                sum = sum - a[i];
                i++;
                j++;
            }
        }
        return maxValue;
    }
}
