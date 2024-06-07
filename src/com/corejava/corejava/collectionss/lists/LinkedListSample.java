package com.corejava.corejava.collectionss.lists;


import java.util.LinkedList;
import java.util.List;

public class LinkedListSample {

    // LinkedListSample in java is double linked list list of Nodes
    // linkedlist is a data structure used to implelement stacks adn queeu
    // use when there is a lot of addition and deletion
    public static void main(String[] args) {

   LinkedList linkedList = new LinkedList<>();

   linkedList.addFirst("rohit");
   linkedList.addLast("prashar");
        linkedList.add(10); // keeps on adding at the next node
        linkedList.add(20); // 20 will have information about 10 and 30
  // [10,20]
        System.out.println(linkedList);
     //   linkedList.set(0,"hello");//["hello,20"]
        linkedList.add(50);
    //    linkedList.add(0,"rohit");// [rohit,hello,20,50]
        System.out.println(linkedList);
        linkedList.removeFirst(); //constant time
        linkedList.removeLast();
        System.out.println();
        linkedList.removeFirstOccurrence("rohit");
        linkedList.removeLastOccurrence("rohit");
        System.out.println(linkedList);

        // Queue methods
        linkedList.offer("sydne");
        linkedList.offerFirst("india");
        System.out.println(linkedList);

    }
}
