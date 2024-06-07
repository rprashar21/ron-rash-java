package collections.maps.HashMapSample;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapMethods {
    public static void main(String[] args) {


        HashMap<String,Integer> map = new HashMap<>();
        map.put("Apple", 50);
        map.put("Banana", 30);
        map.put("Cherry", 20);

        // iterat thru the map

        for(Map.Entry<String,Integer> entrySet : map.entrySet()){
            System.out.println("key :: "+ entrySet.getKey() + "value :: " + entrySet.getValue());
        }

        // keys should be unique if object should ovveride the equals and hashcode methods
        Set<String> keySet = map.keySet();
        Collection<Integer> values = map.values();

        for(Integer element : values)
        {
            System.out.println(element);
        }

        // put if and getorDefault
        System.out.println(map.getOrDefault("cherry",100));

        map.putIfAbsent("Cherry",20);
        map.putIfAbsent("Orange",200);
//        map.
//        System.out.println(map);
    }
}
