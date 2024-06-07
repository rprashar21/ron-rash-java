package collections.maps;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapSample {

    // map(i) --> sortedmap(i)-->navigable map(i)-->treemap(c)
    // duplicatie keys not allowed becoz they are unique but values can be
    // collection views of map
    // Set -- map.keySet() ,
    // Collection map.values()
    // Set map.entrySet

    /*
    *          interface Map
    *             {
    *                  interface Entry
    *                 {
    *                     object getKey()
    *                     object getValue()
    *                     }
    *                }

    * */
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>() ; // create empty hashMap object with default size as 16

        // HashMap is not synchronized ,, we can make it synchronized using Collections.synchronized()
        Map  map1 = Collections.synchronizedMap(map); // map1 is synchronized and map is not synchronized
         map.put("rohit",500);
        map.put("swati",800);
        map.put("rohit",900); // this will be replaced ,, string object string object implements equals and hashcode

        // loop thru the map
        Set<String> keyset = map.keySet();
        System.out.println(keyset);

        Collection<Integer> values = map.values();
        System.out.println(values);

//       for(Map.Entry<String,Integer> entry : map.entrySet()){
//           System.out.println(entry.getKey() + " "+entry.getValue() );
//       }
//
       map1.forEach((s, value) -> System.out.println(s+" "+value) );

        Set set = map1.entrySet();
        System.out.println(set);



    }

}
