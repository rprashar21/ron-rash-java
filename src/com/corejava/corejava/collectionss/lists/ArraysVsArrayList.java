package com.corejava.corejava.collectionss.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysVsArrayList {

    /*
     *    Arrays            vs             ArrayList
     *  Arrays.asList() --
     *  mutable list which is nit resizable   List.of is immutable
     *
     *
     *
     *
     *
     *
     * */
    public static void main(String[] args) {

        int[] array = new int[10];
        array[0] = 1;

        int[] a = new int[]{1, 2, 4};

        int[] arr = {1, 2, 3, 4};

        ArrayList<Integer> list = new ArrayList<>();
        // this will create an immutable list
        ArrayList<Integer> integers3 = new ArrayList<>(List.of(1, 2, 3, 5));

        //lenght
        int length = array.length;
        int size = list.size();

        // get an elemnt
        int i = array[0];
        int listelem = integers3.get(0);

        // 2d array
        int[][] array2d = {
                {10,20}, {40,50}, {100,200}
        };

        List<List<Integer>> lists = new ArrayList<>();

        // printing
        System.out.println(Arrays.deepToString(array2d));
        System.out.println(lists);

        // binarySearch in array
        int search = Arrays.binarySearch(arr, 3); // arr has to be sorted
        System.out.println(search);

        List<Integer> asList = Arrays.asList(30, 100, 900);
        List<Integer> integers = List.of(2, 6, 9);
        asList.set(0,900);//replaces element at index 0
//        asList.add(800); throws exception
        System.out.println(asList);
        integers3.set(0,200);// not allwo
        System.out.println(integers3);



    }
}
