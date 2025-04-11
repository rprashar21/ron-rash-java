package collections.list;

import java.util.ArrayList;
import java.util.List;

public class ListIterator {

    // listIterator is an interface in java that can go in bot forward and reverse direction
    // Its bidirectional ,, can modify a list add, remove or set
    // acces via index nextIndex or previous Index

    // when to use
//    ou need to go both forward and backward in a list
//
//    You want to modify the list while iterating (safely)
//
//    You need to know the position/index of elements during iteration
// if you try to modify a list using a for-each loop or a simple Iterator, you will get concurrentmodifcation execption
// But ListIterator is built for safe editing while traversing


    public static void main(String[] args) {

        List<String> words = new ArrayList<>();
        words.add("This");
        words.add("is");
        words.add("teh");
        words.add("best");
        words.add("day");

        java.util.ListIterator<String> iterator = words.listIterator();

        while(iterator.hasNext()){
            final String word = iterator.next();

            if(("teh").equals(word)){
                iterator.set("the"); // // Replaces "teh" with "the" we are modifyin the list when doin the iterationn
            }
        }

        System.out.println(words);

        // we can also go in reverse
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
            iterator.remove();
        }
        System.out.println("removed all the words from the list "+words);
    }
}
