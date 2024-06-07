package datastructures.arrays.leetcodeArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomizedSet {

    HashMap<Integer, String> map;
    List<Integer> values= new ArrayList<>();
    Random random;

    public RandomizedSet() {
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, "P");
            return true;
        }

    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            map.remove(val);
            return true;
        }
    }

    public int getRandom() {
   // map all the value to a list
        random= new Random();
        if(map.size()>0)
        {
            Set<Integer> set = map.keySet();

            for(int elem : set)
            {
                values.add(elem);
            }
        }
        return values.get(random.nextInt(values.size()));

    }


    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.print(obj.insert(-1)+" ");
        System.out.print(obj.remove(-2)+" ");
        System.out.print(obj.insert(-2)+" ");
        System.out.print(obj.getRandom()+" ");
//        System.out.print(obj.getRandom()+" ");
        System.out.print(obj.remove(-1)+" ");
        System.out.print(obj.insert(-2)+" ");

        System.out.print(obj.getRandom()+" ");
//        System.out.print(obj.getRandom()+" ");
//        System.out.print(obj.insert(0)+" ");
//        System.out.print(obj.remove(0)+" ");
    }
}
