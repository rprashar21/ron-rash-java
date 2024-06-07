package collections.maps.HashMapSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapIntro {

    // HashMap can have a List or object as keys but need to make sure that the hashcode does not change
    // ifu modifiy the list the hashcode will change which
  //      When you try to retrieve the value using the modified list as a key, you might not find it because the hashCode of the list has changed.

    public static void main(String[] args) {

        Map<List<Integer>, String> map = new HashMap<>();

        List<Integer> keyList = new ArrayList<>(Arrays.asList(1, 2, 3));
        map.put(keyList, "Example Value");

        // Step 3: Retrieve the value using the list as a key
        System.out.println("Value for original key: " + map.get(keyList));

        // Step 4: Modify the list and try to retrieve the value again
        keyList.add(4);
        System.out.println("Value for modified key: " + map.get(keyList));
    }
}
//    Behind the scenes, when you use a List as a key in a HashMap in Java,
//    several important things happen, particularly related to how HashMap uses hashCode() and equals() methods for key objects:
//
//        Storing the Key-Value Pair:
//
//        When you put a key-value pair in the HashMap,
//        the hashCode() method of the key object (in this case, the List) is called.
//        This method computes a hash code, which HashMap uses to determine where to store the entry internally.
//        The List's hash code is determined by the contents of the list.
//        The default hashCode() implementation for lists in Java calculates the hash code based on the hash codes of all the elements in the list.
//        Retrieving the Value:
//
//        When you try to retrieve a value from the HashMap using a key, the HashMap again calls the hashCode() method of the key.
//        It uses this hash code to find where the value should be stored.
//        If it finds a bucket (internal storage location) with one or more entries,
//        it then uses the equals() method to find an exact match among these entries.
//        Modifying the List Key:
//
//        If you modify the list after it's been used as a key (like adding a new element),
//        its hash code changes because it's dependent on the contents of the list.
//        This means that when you try to retrieve the value using this modified list,
//        the HashMap calculates a different hash code than it did when storing the entry.
//        As a result, it looks in the wrong place and likely doesn't find the correct entry.
//        Thus, you end up with a situation where the map entry is virtually "lost" because the key now points to a different hash code.
//        This behavior underscores the importance of using immutable objects as keys in HashMap to maintain consistent behavior.
//        Modifying a key after it's been used to store a value in a HashMap is a common source of bugs in Java programs