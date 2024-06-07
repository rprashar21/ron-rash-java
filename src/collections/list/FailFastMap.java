package collections.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FailFastMap {

    public static void main(String[] args) {

//        Map<Integer,String> map = new HashMap<>();
//        map.put(1,"rohit");
//        map.put(2,"swati");
//
//        Iterator it = map.keySet().iterator();
//
//        while(it.hasNext())
//        {
//            Integer key = (Integer) it.next();
//            System.out.println(map.get(key));
//            map.put(3,"myra"); // adding while iterating ,, shoud use a concurrent hasmap instead as this will throw an error
//        }
        Map<Integer,String> map = new ConcurrentHashMap<>();
        map.put(1,"rohit");
        map.put(2,"swati");

        Iterator it = map.keySet().iterator();

        while(it.hasNext())
        {
            Integer key = (Integer) it.next();
            System.out.println(map.get(key));
            map.put(3,"myra"); // adding while iterating ,, shoud use a concurrent hasmap instead as this will throw an error
        }
    }
}
