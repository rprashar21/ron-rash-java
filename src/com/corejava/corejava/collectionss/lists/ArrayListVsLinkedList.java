package com.corejava.corejava.collectionss.lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayListVsLinkedList {
    //    Al                    linkedList
    // retreival process --     insertiona and deltion
    // worst                   best

    public static void main(String[] args) {
        ArrayList<String> groceries = new ArrayList<>();
        groceries.add("oranges");
        String [] items = {"oranges ","banana","apples"," "};
      //  groceries.addAll(List.of(items));

        //
        for(String item :items)
        {

            String trimmed = item.trim();
            // check f the element ialready present in the list
            if(groceries.indexOf(trimmed)<0)
            groceries.add(trimmed);
        }

        System.out.println(groceries);
        // sort this
        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);
        // sort in reverse order
        groceries.sort(Comparator.reverseOrder());
        System.out.println(groceries);

        groceries.remove("appless");
        System.out.println(groceries);
    }
}
