package datastructures.arrays.arraysHashing;

import java.util.HashMap;
import java.util.Map;

public class ConatinsDuplicate {

    public static void main(String[] args) {
        int[] a ={1,2,3,1};

        boolean b = containsDuplicate(a);
        System.out.println(b);
    }

    private static boolean containsDuplicate(final int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(Integer i :a ){
          //  map.put(i, map.get(i)!=null? map.get(i)+1 :1 );
          //  map.put(i, map.getOrDefault(i,0)+1);
            map.put(i, map.containsKey(i)? map.get(i)+1 :1 );
        }

        System.out.println(map);

       for(Map.Entry<Integer,Integer> entry: map.entrySet()){

           if(entry.getValue()>1)
           {
               return true;
           }
        }
       return false;
    }

}
