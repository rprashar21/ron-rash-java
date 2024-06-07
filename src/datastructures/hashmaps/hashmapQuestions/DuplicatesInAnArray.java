package datastructures.hashmaps.hashmapQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesInAnArray {

    public static void main(String[] args) {

        int a[] = new int[]{1, 1, 2};

        // pudo
        // loop thru and keep adding the count of the array in a map
        // when we find that an elemnt exist in the map we return or do whatever is asked

        // if we have to emit the value then use a Set

        //

//        findCountOfEachWord(a);
//        findDuplicates(a);
        inplaceArray(a);

        // removing duplicates in an array
        // using set
        // using hashmap if the entry i already there skip 
    }

    private static int inplaceArray(final int[] nums) {

        int[] temp = new int[nums.length];
        int k=0;
        for(int i=0;i<nums.length;i++){
            if(temp[k]!=nums[i])
            {
                temp[k++]=nums[i];
            }
        }
        System.out.println(Arrays.toString(temp));

        return 1;
    }

    private static void findCountOfEachWord(final int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], map.get(a[i]) != null ? map.get(a[i]) + 1 : 1);
        }
        System.out.println(map);
    }

    public static Set<Integer> findDuplicates(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }
        System.out.println(duplicates);
        return duplicates;
    }
}
