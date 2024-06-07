package collections.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListSample {
    // difference between list I and Set(i)
    // arraylist and linkedlist --> accessing and storing data in arraylist -- linkedlist main manipulatiing data is bertter
    // arraylist internally uses dynamicarray and linked used soubly linked lsist
  // About set -- , comparabke and comparator

  // what is fail fast and fail safe iterator ??
  // an iterator which fails fast does not allow modifcation while iterating like arraylist, hashmap  Vector

  // fail safe -- allows us to modify while iterating   --> CopyOnWriteArrayList, ConcurenHashMap;


//    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<String>  strings = new ArrayList<>(); // runTime polymorphism --> code reusability , flexability ,absatraction ,encapsulation,


        // arrayList we can store dupicales and set we cannot major difference
        //i.e  create a customArrayList and extend it modify the add method so that duplicates cannot be added

        CustomArrayList customArrayList = new CustomArrayList();
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.add(1);

        System.out.println(customArrayList); // only 1,2 are added

        Set<String>  strings1 = new HashSet<>();
        strings1.add("rohit");
        strings1.add("rohit");
        strings1.add("jack"); //  [rohit,jack]

        // Set does not allow duplicate ,, internall uses a map to store objects and uses the object which we insert as a key and
        // key in maps cannot be duplicate becoz of equals method
        // internal implementation
//    public boolean add(E e) {
//        return map.put(e, PRESENT)==null;

//        public boolean add(E e) {
//            return map.put(e, PRESENT)==null;
//        }

        // if we use set with a custom object we need to make sure that the object follows the contract of equals and hashcode method
        // set will not allow primitive and wrapper class duplicate values ,, but wil allow custom objects unless the override the equals and hashcode


    }
}
