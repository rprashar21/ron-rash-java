package datastructures.arrays.arraysHashing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TopKElements {
    public static void main(String[] args) {
       int[] nums = {1,2,2,3,3};
       int k = 2;
        topKFrequent(nums,k);
        practice(nums,k);

        HashMap<Integer, Integer> map =new HashMap<>();
        Integer orDefault = map.getOrDefault(1, 0);
        System.out.println("prints the default value "+orDefault);

    }

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();

        int[] output = new int[k];
        for(int i :nums)
        {
            map.put(i,map.get(i)!=null?map.get(i)+1:1);
        }

        // create a custom Comparator
        Comparator<Integer> valueComparator = (obj1, obj2) -> map.get(obj1)> map.get(obj2)?-1:1;

        Map<Integer, Integer> sortedMap = new TreeMap<>(valueComparator);
        sortedMap.putAll(map);

        System.out.println(sortedMap);

        for(Map.Entry<Integer,Integer> entry: sortedMap.entrySet())
        {
            if(k>0){
                k=k-1;
                output[k]=entry.getKey();
            }

        }
        System.out.println(Arrays.toString(output));
        return output;
    }
    private static int[] practice(int[] a ,int k){
        // put all the values in a hashmap
        // sort the map based on the value decreasing order
        // loop thru the hashmap and chekc if the count == k ,, if yes put it in the sesulting array

        HashMap<Integer, Integer> map = new HashMap<>();

        // if the value is not present it return a default value of 0
        Arrays.stream(a).forEach(elem->map.put(elem, map.getOrDefault(elem,0)+1));



        System.out.println("initial "+map);
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>((v1,v2)-> map.get(v1)> map.get(v2)?-1:1);
        sortedMap.putAll(map);
        System.out.println("after sorting map values based on value "+sortedMap);
        int[] result = new int[k];
        final AtomicInteger i = new AtomicInteger();
        sortedMap.entrySet().forEach(entry->{

            if(entry.getValue()==k)
            {
                result[i.getAndIncrement()]= entry.getKey();
            }

        });
        System.out.println(Arrays.toString(result));
       return result;
    }

}

