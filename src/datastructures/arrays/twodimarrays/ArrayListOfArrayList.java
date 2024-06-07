package datastructures.arrays.twodimarrays;

import java.util.ArrayList;

public class ArrayListOfArrayList {


    public static void main(String[] args) {
        // Create an ArrayList of ArrayLists
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        // Populate the ArrayList of ArrayLists using a for loop
        for (int i = 0; i < 2; i++) { // outer Array LIst whihc will contain 2 lists
            ArrayList<Integer> innerList = new ArrayList<>();
            for (int j = 1; j <= 4 - i; j++) {
                innerList.add(i * 4 + j);
            }
            list.add(innerList);
        }

        // Print the contents of the ArrayList of ArrayLists
        System.out.println("ArrayList of ArrayLists: " + list);


        ArrayList<ArrayList<Integer>> listArrayList = new ArrayList<>();

      for(int i=0;i<4;i++){ // outerlist 4 times run
          ArrayList<Integer> innerList = new ArrayList<>() ;
          for (int j = 1; j <=4-i; j++) { // here inner list will contain 4 elements


              innerList.add(j * 4 + j);

          }
          listArrayList.add(innerList);
      }

        System.out.println(listArrayList);
     }

}
