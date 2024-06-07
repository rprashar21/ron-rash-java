package datastructures.hashmaps.hashmapQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /*
    * Given an array
    * [2,7,11,15], target =9
    *  a[0]+a[1] = target -- o/p -- [0,1]
    *
    *    map-- K,V
    * */
    public static void main(String[] args) {
        int[] array ={3,2,3};   //
        int target=6;
        final int[] ints = findIndices(array, target);

        System.out.println(Arrays.toString(ints));
        System.out.println(twoSum(array,target));
    }

    private static int[] twoSum(final int[] nums,int target) {
        Map<Integer,Integer> map = new HashMap<>();  // map is empty    2,0     ,

//        for(int i=0;i< array.length;i++)
//        {
//            int diff = target - array[i];
//            if(map.containsKey(diff))
//                return new int[]{map.get(diff),i};
//
//            map.put(array[i],i);
//        }
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]+nums[i+1]==target)
                return new int[]{i,i+1};
        }
        return new int[]{-1,-1};
    }

    private static int[] findIndices(final int[] array,int target)
    {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] result = new int[2];
        // iterate over the map and keep putting elements with their indices
        for(int i=0;i<array.length;i++)
        {
            int complement = target -array[i];
            if(hashMap.containsKey(complement))
            {
                return new int[]{hashMap.get(complement),i};
            }
            hashMap.put(array[i],i);
        }
        throw new IllegalArgumentException("No two integers add up to the target");
    }
}
