package collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Map {

    public static void main(String[] args) {

        // this is a mutable map

        HashMap<Integer,String> studentMap= new HashMap<>();
        HashMap<Integer,Integer> map = new HashMap<>();

        map.put(1,1);
        map.put(2,2);

        final Integer integer = map.get(3);

        // create an immutable map
        final java.util.Map<Integer, Integer> unmodifiableMap = Collections.unmodifiableMap(map);

        // this throws an error
        // Unmodifiable map is a wrapper around a modifiable map.
        unmodifiableMap.remove(1);
        System.out.println(unmodifiableMap);

        // we are still bale to midify the map
      map.put(3,3);


      // tp make a map unmodifiable use ImmutableMaps
//        ImmutableMap<String, String> immutableMap
//                = ImmutableMap.of("USA", "North America", "Costa Rica", "North America");
//        assertTrue(immutableMap.containsKey("USA"));
//        assertTrue(immutableMap.containsKey("Costa Rica"));

        System.out.println("ky hora hai bhai");
    }
}
