package com.corejava.Generics.classes.assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class GenericMain {
    //Write a generic method to count the number of elements in a collection
    // that have a specific property (for example, odd integers, prime numbers, palindromes).

    private static <T> int countNumberOfElements(Collection<T> c, UPredicate<T> predicate) {
        int count = 0;
        for (T elem : c) {
            if (predicate.test(elem))
                count++;
        }
        return count;
    }

    public interface UPredicate<T> {
        boolean test(T obj);
    }

    private static <T, Y> T max(T x, Y y) {
        // operator cannot be applied to generics this will give a compile time error
        // return x>y?x:y;
        return x;
    }

    // Write a generic method to exchange the positions of two different elements in an array.
    private static <T> void exchangePositionsInArray(T[] array, int post1, int post2) {
        T temp;
        temp = array[post1];
        array[post1] = array[post2];
        array[post2] = temp;
    }

    // Will the following class compile? If not, why?
    public class Singleton<T> {

//        public static T getInstance() {
//            if (instance == null)
//                instance = new Singleton<T>();
//
//            return instance;
//        }
        //You cannot create a static field of the type parameter T.
        //  private static T instance = null;
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        final Integer finalAnswer = GenericMain.countNumberOfElements(integers,(i)->{
            return i==2;
        });
        System.out.println(finalAnswer);

        // second
        Integer[] array = {1, 2};
        System.out.println(array[0]);
        GenericMain.exchangePositionsInArray(array, 0, 1);
        System.out.println(array[0]);
    }
}
