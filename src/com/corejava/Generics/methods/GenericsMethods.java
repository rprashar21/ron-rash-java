package com.corejava.Generics.methods;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericsMethods {
    /*
    Generic methods have a type parameter (the diamond operator enclosing the type) ProductCatalog the return type of the method declaration.
    Type parameters can be bounded
    Generic methods can have different type parameters separated by commas in the method signature.
    Method body for a generic method is just like a normal method.
    */

    // generic method of converting an array to a list

    private static <T> List<T> convertArrayToList(T[] array) {

        // The <T> in the method signature implies that the method will be dealing with generic type T.
        // This is needed even if the method is returning void.

        return Arrays.stream(array).collect(Collectors.toList());
    }
    private static <T, G> List<G> convertArrayToList(T[] array, Function<T, G> mapperFunction) {

        // the method can deal with more than one generic type. Where this is the case, we must add all generic types to the method signature.

        return Arrays.stream(array)
                .map(mapperFunction)
                .collect(Collectors.toList());
    }

    // Counts the number of occurrences of elem in anArray.
    //  how many times this elem is in the array
//
    public  static <T> int countWithoutErasure(T[] anArray, T elem) {
        int count = 0;
        for (T elemInArray : anArray)
            if (elemInArray.equals(elem))
                ++count;
        return count;
    }
//    public static <T extends Comparable<T>> int findFirstGreaterThan(T[] at, T elem) {
//     return 1;
//    }
    // after type erasure
    public static  int findFirstGreaterThan(Comparable[] at, Comparable elem) {
        return 1;
    }
// after type erasure
    public static int count(Object[] anArray, Object elem) {
        int count = 0;
        for (Object elemInArray : anArray)
            if (elemInArray.equals(elem))
                ++count;
        return count;
    }

   // Will the following method compile? If not, why?
    public static void print(List<? extends Number> list) {
        for (Number n : list)
            System.out.print(n + " ");
        System.out.println();
    }

   // What is the following method converted to after type erasure?


    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        List<String> stringList
                = GenericsMethods.convertArrayToList(intArray, integer -> integer.toString());

        System.out.println(stringList);

        GenericsMethods.countWithoutErasure(intArray,2);
        List<Integer> integers =Arrays.asList(1,23);
        GenericsMethods.print(integers);
    }

}


