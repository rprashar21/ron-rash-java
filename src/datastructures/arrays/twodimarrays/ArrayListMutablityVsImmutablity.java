package datastructures.arrays.twodimarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListMutablityVsImmutablity {

    public static void main(String[] args) {

        // Creating an ArrayList from an array
        // this will create a mutable list
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5));
        list1.add(6); // This works fine
        System.out.println(list1); // Output: [1, 1, 2, 3, 4, 5, 6]

// Creating a fixed-size List with a single element
        List<Integer> list2 = Arrays.asList(12);
        //   list2.add(13); // This throws UnsupportedOperationException
        System.out.println(list2); // Output: [12]

        List<String> mutableList = Arrays.asList("rohit");
// Adding a new element to the mutable List will give error
        // mutableList.add("rohit") --> this will give me an error becoz Imtrying to add an element to a fixed size array
        // Arrays are fixed size you can replace an element

        // similarly if you have a List.of
        mutableList.set(0,"john");

        System.out.println(mutableList);

        List<Integer> immutableList = List.of(1, 2, 3);

        // u cannot add or replace the contenst of this list
        List<Integer> newImmutableList = immutableList;
     //   newImmutableList.add(4); this will throw error
        System.out.println(newImmutableList);

        List<String> mutable = Arrays.asList("rohit");
        List<String> newMutable = mutable;

        System.out.println(mutable.hashCode()+ " "+newMutable.hashCode() );
      //  mutable.add("prashar"); both lists are pointing to the same reference
        List<String> anotherMutable = new ArrayList<>(mutable); // but this u cannot do it for a immutable list
        anotherMutable.add("prashar"); // this is fine this has another refence

        System.out.println(anotherMutable.hashCode());
    }
}
