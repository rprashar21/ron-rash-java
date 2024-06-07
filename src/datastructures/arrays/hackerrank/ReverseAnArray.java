package datastructures.arrays.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseAnArray {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);


        reverse(integers);
    }

    private static List<Integer> reverse(final List<Integer> a) {

        for(int i=0;i<a.size()/2;i++)
        {
            swap(a,i,a.size()-1-i);
        }
        System.out.println(a);
        return a;
    }
    static void swap(List<Integer> a,int start,int end)
    {
        int temp = a.get(start);
        a.set(start,a.get(end));
       a.set(end,temp);

    }
}
