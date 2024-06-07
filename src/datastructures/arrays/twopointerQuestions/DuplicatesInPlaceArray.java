package datastructures.arrays.twopointerQuestions;

import java.util.Arrays;

public class DuplicatesInPlaceArray {
    public static void main(String[] args) {
        int[] a = new int[]{1,1,2};
        removeDuplicates(a);
        int uniqueIndex = 1;
        for(int i=1;i< a.length;i++)
        {
            // we start from the index 1 becoz the first element is unique
            if(a[i]!=a[uniqueIndex-1])
            {
                a[uniqueIndex]=a[i];
                uniqueIndex++;
            }
        }
        System.out.println(Arrays.toString(a)+ " lenght is "+uniqueIndex);
    }

    public static  int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0; // Check for empty array

        int uniqueIndex = 1; // Start from the second element
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueIndex - 1]) {
                nums[uniqueIndex] = nums[i];
                uniqueIndex++;
            }
        }
        System.out.println(Arrays.toString(nums)+ " lenght is "+uniqueIndex);
        return uniqueIndex; // Number of unique elements
    }
}
