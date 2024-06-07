package datastructures.leetcode.sortingQuestions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortAndFindTheMedian {
    public static void main(String[] args) {


        List<Integer> arr = Arrays.asList(0,1,2,4,6,5,3);
        findMedian((List<Integer>) arr);
    }

    public static int findMedian(List<Integer> arr) {
        // Write your code here
        // sort
        for(int i =0;i<=arr.size()-2;i++)
        {
            for(int j=i+1;j>0;j--)
            {
                if(arr.get(j)<=arr.get(j-1))
                {
                    int temp = arr.get(j);
                      arr.set(j,arr.get(j-1));
                      arr.set(j-1,temp);
                }
            }

        }
        System.out.println(arr);
        int median = ((arr.size())/2);
        System.out.println(arr.get(median));
        return arr.get(median);
    }

}

