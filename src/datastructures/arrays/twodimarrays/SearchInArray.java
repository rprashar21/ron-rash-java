package datastructures.arrays.twodimarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchInArray {
    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();

        list.add(Arrays.asList(1,3,5,7));
        list.add(Arrays.asList(10,11,16,20));
        list.add(Arrays.asList(23,30,34,50));

        // binary search of
        int search=-1;
        int i =0;
        for(;i< list.size();i++)
        {
           search  = binarySearch(list.get(i), i, list.get(i).size() - 1,3);
            if(search!=-1)
            {
                break;
            }
        }
        System.out.println("row is "+i +"col is "+search);
    }

    private static int binarySearch(final List<Integer> list,  int start,  int end,final int target) {

        while(start<=end)
        {
            int mid = start +(end - start)/2;

            if(target==list.get(mid))
            {
                return mid;
            }
            else if(target>list.get(mid))
            {
                start= mid+1;
            }
            else
            {
                end=mid-1;

            }
        }
        return -1;
    }
}
