package datastructures.binary.Questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KokoEatingBananas {

    //Return the minimum integer k such that she can eat all the bananas within h hours.
    //https://leetcode.com/problems/koko-eating-bananas/

    public static void main(String[] args) {
        int h=6;
        List<Integer> list = Arrays.asList(30,11,23,4,20);

        minEatingSpeed(list,h);
    }

    private static double minEatingSpeed(final List<Integer> list, final int h) {

        // if i want to convert this to an array

        // find the max element in this array to find the range for binary search
        int maxElement = Integer.MIN_VALUE;
        for(int i=0;i<list.size();i++)
        {
             maxElement=Math.max(list.get(i),maxElement );
        }
        List<Integer> range = new ArrayList<>();

         for(int i=1;i<=maxElement;i++)
         {
              range.add(i);
         }
         int start=1;
         int end =maxElement;
        double minEating = binaySearch(range, start, end, list, h);

        System.out.println(minEating);
        return minEating;

    }

    private static double binaySearch(final List<Integer> range,  int start,  int end,final List<Integer> list,final int h) {
        int minElement =Integer.MAX_VALUE;
        while(start<=end) // 30,11,23,4,20
        {
            int middle = start + (end-start)/2;
            int value =0;
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i)/middle==0)
                {
                    value=value+1;
                } else if (list.get(i)%middle==0) {

                    value=value+list.get(i)/middle;
                }
                else
                {
                    value=value+list.get(i)/middle+1;
                }
            }
            minElement=Math.min(minElement,value);
            if(value <=h)
            {
                end = middle-1;
            }
            else
            {
                start = middle+1;
            }
        }
        return start;
    }

}
