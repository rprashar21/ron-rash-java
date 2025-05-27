package datastructures.arrays.twopointerQuestions;

import java.util.Arrays;

public class Twosum2 {

    // 2sum problem without hashmap
    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15,3,6};
        int target = 10;

        // sort the array

        Arrays.sort(a); // 2,3,6,7,11,15

        int left = 0;
        int right = a.length-1;

        // if the left + right > target right-- else left++

        while(left<right)
        {
            int sum = a[left]+a[right];
            if(sum == target)
            {
                System.out.println(left+" "+right);
                break;
            }
            if(sum>target)
              right--;
            else
                left++;
        }

        int[] ints = twoSum(a, 10);
        System.out.println(Arrays.toString(ints));

    }

    public static int[] twoSum(int[] nums, int target) {

        // 2,7,11,15
        // target = 9
        int i = 0;
        int j =nums.length-1;

        if(nums.length>2)
        {
            while(i!=j)
            {
                int sum = nums[i]+nums[j];
                if(sum==target){
                    return new int[]{i,j};
                }
              if(sum>target)
                 j--;
              else
                  i++;
            }
        }
        return new int[]{i,j};

    }

}
