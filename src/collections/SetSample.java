package collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SetSample {

    /*
    * Set Interface in Java is present in java.util package. It extends the Collection interface.
    * It represents the unordered set of elements which doesn't allow us to store the duplicate items.
    * We can store at most one null value in Set. Set is implemented by HashSet, LinkedHashSet, and TreeSet.
    *
    *
    * LinkedHashSet class represents the LinkedList implementation of Set Interface. It extends the HashSet class and implements Set interface.
    *  Like HashSet, It also contains unique elements. It maintains the insertion order and permits null elements.
    *
    * Java TreeSet class implements the Set interface that uses a tree for storage. Like HashSet, TreeSet also contains unique elements.
    * However, the access and retrieval time of TreeSet is quite fast. The elements in TreeSet stored in ascending order
    *
    * Set<data-type> s1 = new HashSet<data-type>();
    Set<data-type> s2 = new LinkedHashSet<data-type>();
    Set<data-type> s3 = new TreeSet<data-type>();
    * */

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        // lets look at the internal implentation of a set
//        public HashSet() {
//            map = new HashMap<>();
//        }
        set.add("Rohit"); // this returns a boolean value it add like this (""Rohit",PRESENT)
        System.out.println(     set.add("Rohit")); // false replace the old value
//        public boolean add(E e) {
//            return map.put(e, PRESENT)==null;
        set.add("Rohit");
//        }
    }
}
