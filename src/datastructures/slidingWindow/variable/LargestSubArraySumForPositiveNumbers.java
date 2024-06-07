package datastructures.slidingWindow.variable;

import java.util.Arrays;

public class LargestSubArraySumForPositiveNumbers {
    public static void main(String[] args) {

        int[] a= new int[]{10,15,-5,15,-10,-5};
        int [] a1 = {4,1,1,1,2,3,5};
        // largets is {{4,1},{1,1,1,2},{2,3},{5} but longest is {1,1,1,2} size is 4

        // sliding window problem with variable size
        int target=5;
        largestSubArraySum(a,target);

        // we can d this using a hashmap
        int[] a2 = {10,15,-5,15,-10,-5};
        subArrayWithSum(a2,target);
    }

    private static void subArrayWithSum(final int[] a, final int target) {
        // do we need to find all the subarrays ??
        int i=0;
        int j=0;
        int sum =0;
        int [] result= new int[2];
        while(j< a.length) {
            sum = sum + a[j];

            if (sum == target)
            {
                result[0]=i;
                result[1]=j;
                break;
            }
            if(sum>target)
            {
                while(sum>target)
                {
                    sum=sum- a[i];
                    i++;
                }
            }
        j++;
        }
        System.out.println(Arrays.toString(result));
    }

    private static void largestSubArraySum(final int[] a, final int target) {

        int i=0;
        int j=0;
        int sum = 0;
        int maxLength=0;
        while (j<a.length)
        {
            sum = sum +a[j];

            if(sum==target)
            {
                System.out.println("value of "+i+" "+j);
                maxLength = Math.max(j-i+1,maxLength);
                sum=sum-a[i];
                i++;
            }
           else if(sum>target)
            {
                while(sum>target)
                {
                    sum=sum-a[i];
                    i++;
                }
            }
            j++;
        }
        System.out.println(maxLength);
    }
}
