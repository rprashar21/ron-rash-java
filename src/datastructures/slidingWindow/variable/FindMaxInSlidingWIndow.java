package datastructures.slidingWindow.variable;

import java.util.Arrays;
import java.util.Deque;

public class FindMaxInSlidingWIndow {


    // here we need to find the maximum in a sliding window
//Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//Output: [3,3,5,5,6,7].

//
//    Input: nums = [1], k = 1
//    Output: [1]

    public static void main(String[] args) {

        // when
      //  int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] a ={1,3,1,2,0,5};
       // int[] a = {1, -1};
        int windowSize = 3;
        // given
        int[] result = findMaxInWindow(a, windowSize);
        // then
        System.out.println(Arrays.toString(result));
        // assertequals
    }

    private static int[] findMaxInWindow(final int[] a, final int windowSize) {

        if (a.length == 1) {
            return new int[]{a[0]};
        }

        int i = 0;
        int j = 0;

        int[] result = new int[a.length - windowSize +1];
        int maxValue = Integer.MIN_VALUE;
        while (j < a.length) {

            if (a[j] > maxValue) {
                maxValue = a[j];
            }
            if (windowSize == j - i + 1) {
                result[i] = maxValue;

                if(a[i]==maxValue)
                {
                    maxValue = Integer.MIN_VALUE;
                    for(int k=i+1; k<=j; k++)
                    {
                        maxValue = Math.max(maxValue, a[k]);
                    }
                }
                i++;

            }
            j++;
        }

        return result;
    }
}
