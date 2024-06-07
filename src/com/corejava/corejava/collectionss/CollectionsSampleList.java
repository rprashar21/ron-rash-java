package com.corejava.corejava.collectionss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;
import java.util.Stack;

public class CollectionsSampleList {
    /*
    *   Collection is an interface ,, Collections is an implemetation class
    *  COllections have utility methods -- like sort
    *
    *   Collection(I) --> LIst(I)--> ArrayList and linkedLIst -- in java is double linkedlIst
    *                 --> Set(I) --> sortedSet(I) -->NavigableSet(I)--TreeSet
    *
    *    LIst<I> -- duplicates are allowed and insertion order is maintained
    *    Collection -- represents a group of individual objects (List ArrayList Vector Stack )
    *    Set -->Insertion order not there duplicate not allowd SortedSet->NavigableSet-> TreeSet HashSet
    *    Map does not come under Collection interface --> group of objects represented as key vaue
    *    HashMap --- Is an implementation
    *    Keys should
    *
    *                                                             Collection(I)
    *             LIst(I)                              Set
    *        ArrayList LinkedLIst Vector Stack     HashSet    SortedSet
    *                                            LinkedHashSet NAvigableSet
    * */

    public static void main(String[] args) {

        /*
          Collections implement Serializable,implements cloneable
          List also implements RandomAccess -- O(1) -- give me 10 th item or 1lakn itme
        * Underling dats stricture is dynamic array
        * duplicates are allowed null is allowed
        * isnertion order is allowed

         for insertion and deletion ArrayList is worse ,, linkedlist is better
        *
        * */

        // group of individual objects --list internall is a dynamic array
        // Arrays.AsList reuturn an immutable list -- where u cannot add or delete emelements
        List<Integer> list = new ArrayList<>();
        list.add(100);        list.add(200);         list.add(300);

        list.add(20); // added to the end of the list
        System.out.println(list);
        list.add(2,34); // this will add 34 at index 2 and shift all the elements to the right
        System.out.println(list);
        list.set(0,900); // set replaces elemnt at a particalur index
        System.out.println(list);
        list.get(4); // get element at a particular index
        list.indexOf(300); // return the index of the object ie its first occurence
        list.lastIndexOf(5); // returns the last index of the object

        // we can convert a hashset or treeset or stack or vector into list
        // so collections undder collection can be converted into each other

        Stack<Integer> stack = new Stack<>();
        stack.push(10);

        // we can convert this to list
        ArrayList<Integer> integers = new ArrayList<>(stack);

        System.out.println(integers);

        ArrayList<Integer> list1 = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        System.out.println(list1 instanceof Serializable);// true
        System.out.println(list1 instanceof Cloneable); // true
        System.out.println(list1 instanceof RandomAccess); // true
        System.out.println(linkedList instanceof RandomAccess); // false



    }
}
