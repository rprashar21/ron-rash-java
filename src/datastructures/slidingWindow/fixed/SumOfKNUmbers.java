package datastructures.slidingWindow.fixed;

public class SumOfKNUmbers {

    public static void main(String[] args) {
        int[] a = new int[]{-2, 5, 1,-2};
        // maximum sum of  subarray with 3 elements

        maximumSumOfSubArray(a,2);
    }

    private static void maximumSumOfSubArray(final int[] a,final int k) {
        int i=0;
        int maxSum= Integer.MIN_VALUE;
        int sum = 0;
        int j=0;
        while(i<=a.length-k) // or j<a.length -- becoz j will travel till the end of the array
        {
            sum = sum + a[j];

            if(j-i+1==k)
            {
                maxSum = Math.max(maxSum,sum);
                sum= sum - a[i];
                i++;
                j++;
            }
            else {
                j++;
            }
        }

        System.out.println(maxSum);
    }

}
