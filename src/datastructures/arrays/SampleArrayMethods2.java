package datastructures.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleArrayMethods2 {

    public static void main(String[] args) {


        // sort an aray

        // sample array methods
        // copyOfRange
        String[] array = {"a","b","c","d"};
        String[] strings = Arrays.copyOfRange(array, 0,array.length);// index 2 to 3 3 is exclusive

        System.out.println(Arrays.toString(strings));

        int [] arrays = new int[10]; // it not a dynamic array we have to specify the length
        // list is nothing but a dynamic array
        List list = new ArrayList<>(20);//[null,null]
          list.add(20);
          list.add("s");
        System.out.println(list);
    }
}
