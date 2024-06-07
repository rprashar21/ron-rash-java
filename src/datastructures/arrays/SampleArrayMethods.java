package datastructures.arrays;

import java.util.Arrays;

public class SampleArrayMethods {

    public static void main(String[] args) {
        int[] array = new int[10]; // by default all the values will be 0 becuase of default ibt value
        //   Integer [] array = new Integer[10];  this will give null values as these are objects
        System.out.println(Arrays.toString(array));

        // fill items in the array
        Arrays.fill(array, 5); // this will fill with values of 5
        System.out.println(Arrays.toString(array));

        // copy the elements
        int[] temp = new int[array.length];
        int[] secondArray = Arrays.copyOf(array, 2);
        int[] thirdArray = Arrays.copyOf(array,array.length);
        System.out.println(Arrays.toString(secondArray)); // the second array has a length of 2

        int[] newArray  = Arrays.copyOf(array, array.length);
        System.out.println(Arrays.toString(thirdArray)); // third array of same elements as seond array

        int[] fourthArray = Arrays.copyOf(thirdArray, 20);// length of third array is 10 -- so it will copy all the elemnst into the 4th arrray and remaining places will be defaulted
        System.out.println(Arrays.toString(fourthArray));

        // Binary Seacrh using arrays
        String[] names = {"rohit", "swati", "mark", "apple", "julia", "hulia"};
        Arrays.sort(names); // sorting the array ,, becoz binary seacrh works on this
        if (Arrays.binarySearch(names, "juliaa") >= 0) {
            System.out.println("element found in the array");
        }
        else {
            System.out.println("not found");
        }

        // check if two arrays are equal
        // 2 arrays are equal if lenth and elements are same in same position

        int[] a1 = {10,20,30};
        int[] a2 = {10,20,30};

        if(Arrays.equals(a1,a2))
        {
            System.out.println("equal");
        }
        else
        {
            System.out.println("not equal ");
        }

        String result = Arrays.equals(a1,a1)?"equal":"not equal";
        System.out.println(result);

        System.out.println(Arrays.equals(a1,a2));
    }
}
