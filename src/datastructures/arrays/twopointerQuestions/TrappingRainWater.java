package datastructures.arrays.twopointerQuestions;

import java.util.Arrays;

public class TrappingRainWater {
//  Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
// In this case, 6 units of rain water (blue section) are being trapped

    // 2 techniques ist is having an auxiliry arrays and 2nd is two pointer technique



    public static void main(String[] args) {
      // int[] a = {3,1,2,4,0,1,3,2};
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
     //   int[] a = {3,1,0,1,2,1,0,3,0};

    //    extraSpaceSoultion(a);
        twoPointerSolutuion(a);

    }

    private static void twoPointerSolutuion(final int[] a) {

        int maxLeft = a[0];
        int maxRight = a[a.length - 1];
        int left = 1;
        int right = a.length - 2;
        int sum = 0;
        while (left <= right) {
            if (maxLeft < maxRight) {
                int units = maxLeft - a[left];
                if (units > 0) {
                    sum += units;
                }

                if (a[left] > maxLeft) {
                    maxLeft = a[left];
                }
                left++;
            } else {
                int units = maxRight - a[right];
                if (units > 0) {
                    sum = sum + units;
                }
                if (a[right] > maxRight)
                    maxRight = a[right];

                right--;
            }
        }
        System.out.println(sum);
    }

    private static void extraSpaceSoultion(final int[] a) {

        // find maximum and minimum left
        int len = a.length;
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        maxLeft[0] = a[0];
        int maxLeftest = a[0];
        maxRight[len - 1] = a[len - 1];
        int maxRightest = a[len - 1];
        int sum = 0;
        // loop thru this array and find the maximu letf and rght for each given side
        int k = 1;
        int j = len - 2;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > maxLeftest) {
                maxLeft[k++] = a[i];
                maxLeftest=a[i];
            } else
                maxLeft[k++] = maxLeftest;
        }
        for (int i = len - 2; i >= 0; i--) {
            if (a[i] > maxRightest) {
                maxRight[j--] = a[i];
                maxRightest=a[i];
            } else
                maxRight[j--] =maxRightest;
        }

        // loop thru both arrays a nd find the minimum of tw
        for (int i = 0; i < a.length; i++) {
            if (maxLeft[i] < maxRight[i]) {
                sum = sum + (maxLeft[i]-a[i]);
            } else
                sum = sum + (maxRight[i]-a[i]);
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(maxLeft));
        System.out.println(Arrays.toString(maxRight));
        System.out.println(sum);
    }


}
