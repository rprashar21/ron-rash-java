package com.corejava.corejava.collectionss.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ListSample {
    public static void main(String[] args) {
         /*
          Collections implement Serializable,implements cloneable
          List also implements RandomAccess -- O(1) -- give me 10 th item or 1lakn itme
        * Underling dats stricture is dynamic array
        * duplicates are allowed null is allowed
        * isnertion order is preserved

         for insertion and deletion ArrayList is worse ,,
         linkedlist is better becoz it has address of next nodes
         list is not synchronized hence performance is best
         
        *
        * */

        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);

        List<Integer> list1 = new ArrayList<>();
        list1.addAll(list);

        System.out.println(list1);

        List<Integer> list3 = new ArrayList<>();

        list3.add(4);

        list1.retainAll(list3);
        System.out.println(list1);


        // synchronized list
     String[] array ={"kiwi","apples"};
        List<String> fruits = List.of(array); // immutable list
        System.out.println(fruits);
        int apples = fruits.indexOf("apples");
        System.out.println(apples);

        //   fruits.add("banana"); // we will get an error

        ArrayList<String> groceries = new ArrayList<>(fruits);
        groceries.add("milk");
        System.out.println(groceries);

        // sort as well
        Collections.sort(groceries);
        System.out.println(groceries);
        groceries.sort(Comparator.reverseOrder());
        System.out.println(groceries);

        // convert this list to an array
        String[] items = groceries.toArray(new String[groceries.size()]);
        System.out.println(Arrays.toString(items));

//        List<Integer> list = new ArrayList<>();
      //  System.out.println(list.get(0));


    }
}
