package datastructures.arrays.arrayQuestionsleetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Intersection {

    public static void main(String[] args) {
        // intersection  of two lists
        Integer[] array1 = new Integer[]{4, 1, 2, 3, 4, 5};
        Integer[] array2 = new Integer[]{2, 2, 4, 5, 6};

        // intersection means common elements in these 2 arrays

        Set<Integer> integers = intersectionBruteForce(array1, array2);
        System.out.println(integers); // 2,4,5

        // the time colplexity is o(m*n)
        // space complexity is o(n) -- intersection set and array to a list conversion

        // if the arrays are sorted
        Integer[] array3 = new Integer[]{1, 1,10};
        Integer[] array4 = new Integer[]{1, 2, 3, 10};

        intersectionForSortedSet(array3,array4);
    }

    private static void intersectionForSortedSet(final Integer[] array1, final Integer[] array2) {
        int i=0;
        int j=0;
        Set<Integer> set = new HashSet<>();

        while(i< array1.length && j< array2.length)
        {
            if(array1[i]==array2[j])
            {
                set.add(array1[i]);
                i++;
                j++;
            } else if (array1[i]<array2[j]) {
                  i++;
            }
            else
                j++;
        }
        System.out.println(set);
    }

    private static Set<Integer> intersectionBruteForce(final Integer[] array1, Integer[] array2) {

        Set<Integer> intersectionSet = new HashSet<>();
        Set<Integer> nonIntersectionSet = new HashSet<>();

        // loop thru this array and check in the other array if the element is present or not

        List<Integer> list = Arrays.asList(array2);
        for (Integer elem : array1) {
            if (list.contains(elem))
                intersectionSet.add(elem);
        }
        return intersectionSet;
    }
}
