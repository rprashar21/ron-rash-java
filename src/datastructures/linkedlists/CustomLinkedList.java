package datastructures.linkedlists;

import java.util.ArrayList;
import java.util.LinkedList;

public class CustomLinkedList {

    // Single Linked List
    // 1-->2-->10-->20
    // {
    // data and reference
    //    }
    // value and node or address of the next node
    // manipulation of data
    // there maybe holes in a array and we need to shift all elements but not in linkedlist
    // we just need to access the first node of the list and then we can access the
    // bad for search and good for insertion adn deleteion at starting or middle elenets

    // using LinkedListSample in java
    // this should be used when we have to manipulate data at starting and ending position
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        //
        long now = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            linkedList.addFirst(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("linkedList  "+" "+(end - now));

        ArrayList<Integer> arrayList = new ArrayList<>();

        now = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            arrayList.add(0,i);
        }
        end = System.currentTimeMillis();
        System.out.println("arrayList "+(end - now));


        // but if we add elements at the end of array it is fast
        now = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            arrayList.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("arrayList "+(end - now));
    }
}
