package datastructures.arrays.leetcodeArrays;

import java.util.HashMap;
import java.util.Map;

public class MajorityElemnt {

//    max frequcenu in an arraay ina array

    public static void main(String[] args) {
        int a[] = {3, 3, 4};

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int elem : a) {
            map.put(elem, map.getOrDefault(elem, 0) + 1);
        }
        System.out.println(map);
        // iterate thru map
        int maxValue = Integer.MIN_VALUE;
        int maxKey = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }

        }
        System.out.println("max freq " + maxKey);
    }
}
