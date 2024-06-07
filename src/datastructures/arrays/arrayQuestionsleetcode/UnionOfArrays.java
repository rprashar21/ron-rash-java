package datastructures.arrays.arrayQuestionsleetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnionOfArrays {
    public static void main(String[] args) {

        // union of two lists
        int[] array1 = new int[]{4, 1, 2, 3, 4, 5};
        int[] array2 = new int[]{2, 2, 4, 5, 6};

       byUsingASet(array1,array2);
       byUsingAList(array1,array2);

    }

    private static void byUsingAList(final int[] array1, final int[] array2) {
        int i=0;
        int j=0;
        // we will insert the elements in this list
        // compare the 2 lists
        List<Integer> list = new ArrayList<>();
        while(i< array1.length && j< array2.length)
        {
            if(array1[i]<=array2[j])
            {
                if(list.isEmpty() || !list.contains(array1[i]))
                {
                    list.add(array1[i]);
                }
                i++;
            }
            else
            {
                if(list.isEmpty() || !list.contains(array2[j]))
                {
                    list.add(array2[j]);
                }
                j++;
            }

        }

        while(i< array1.length)
        {
            if(!list.contains(array1[i]))
            {
                list.add(array1[i]);
            }
            i++;
        }
        while(j< array2.length)
        {
            if(!list.contains(array2[j]))
            {
                list.add(array2[j]);
            }
            j++;
        }
        System.out.println(list);
    }

    private static void byUsingASet(final int[] array1, final int[] array2) {
        // simple is by taking a Set
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array1.length; i++) {
            set.add(array1[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            set.add(array2[i]);
        }
        System.out.println(set);
    }
}
