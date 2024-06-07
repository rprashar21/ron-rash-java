package datastructures.arrays.leetcodeArrays;

import java.util.Arrays;

public class RemoveElement {

    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
     //   removeElement(a, val);

        removeElementInplaceArray(a,val);
    }

    private static  int removeElementInplaceArray(final int[] nums, final int val) {
        int k=0;
        int newLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[newLength] = nums[i];
                newLength++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return newLength;
    }


    public static int removeElement(int[] nums, int val) {

        int count = 0;
        int[] temp = new int[nums.length];
        int k = 0;
        for (int elem : nums) {
            if (elem != val) {
                temp[k++] = elem;
                count = count + 1;
            }

        }

        nums = temp;
        System.out.println(Arrays.toString(nums));
        System.out.println("count is " + count);
        return count;
    }
}
