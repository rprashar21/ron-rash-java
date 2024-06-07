package datastructures.arrays.twopointerQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestWordDistanceinCircularArray {

    public static void main(String[] args) {
                                             //  0   1     2    3   4    5     6
        List<String> stringList = Arrays.asList("a0","a1","a2","a3","a4","a5","a6");
        int startIndex =5;
        String target = "a1";

        // concatenating the array we can get to the
        shortestWordDistance(stringList,target,startIndex);
        shortestWordDistanceUsingOneLoop(stringList,target,startIndex);
        int targetIndex =0;
        int distance=0;
        for(int i=0;i<stringList.size();i++)
        {
            if(stringList.get(i).equals(target))
            {
                targetIndex= i;
           distance  = Math.abs(startIndex- targetIndex);
              break;
            }
        }
       int leftOver =stringList.size()- distance;
        int min = Math.min(leftOver, distance);
        System.out.println("shotest route is "+min);
    }

    private static void shortestWordDistanceUsingOneLoop(final List<String> stringList, final String target, final int startIndex) {
        /*               k
        *           xo-------------x1
        *  |--------------------------------------|
        *               y
        *
        * if entire part is y xo-x1 is k ,, remaining part is y-k
        *  so we need to find whiich ois min y-k or k ,, becoz then u can be sure u have covered the entire array
        * */
        int ans =Integer.MAX_VALUE;
        for(int i=0;i<stringList.size();i++)
        {
            if(stringList.get(i).equals(target))
            {
                int diff  = Math.abs(startIndex - i);
                int leftPart = Math.abs(stringList.size()-diff);
                ans = Math.min(ans,leftPart);
            }
        }
        if(ans==Integer.MAX_VALUE)
        {
            System.out.println("answer is "+ -1);
        }
        System.out.println(ans);
    }

    private static void shortestWordDistance(final List<String> stringList, final String target,  int startIndex) {

        /// we concatenat to left and right and then calculate the distance;

        List<String> concatenatedList = new ArrayList<>();
        concatenatedList.addAll(stringList);
        concatenatedList.addAll(stringList);
        concatenatedList.addAll(stringList);
        System.out.println(concatenatedList);

        int right=0;
        int left =0;
        int min = Integer.MAX_VALUE;
        startIndex = startIndex+stringList.size();
        for(int i=startIndex;i<concatenatedList.size();i++)
        {
            if(concatenatedList.get(i).equals(target))
            {
                break;
            }
            right++;
        }
        for(int i=startIndex;i>=0;i--)
        {
            if(concatenatedList.get(i).equals(target))
            {
                break;
            }
            left++;
        }
          min = Math.min(left,right);
        System.out.println(min);
    }

    private static void shortestWordDistanceOptimal(final List<String> stringList, final String target, int startIndex) {
        int n = stringList.size();
        int[] dist = new int[n];

        // calculate distances in forward direction
        int prev = startIndex;
        for (int i = startIndex; i < n + startIndex; i++) {
            int curr = i % n;
            if (stringList.get(curr).equals(target)) {
                dist[curr] = 0;
                prev = curr;
            } else {
                dist[curr] = Math.min(dist[prev] + 1, n - dist[prev] + curr - prev);
            }
        }

        // calculate distances in backward direction
        prev = startIndex;
        for (int i = startIndex; i >= startIndex - n; i--) {
            int curr = (i + n) % n;
            if (stringList.get(curr).equals(target)) {
                dist[curr] = 0;
                prev = curr;
            } else {
                dist[curr] = Math.min(dist[prev] + 1, n - dist[prev] + prev - curr);
            }
        }

        // print the minimum distance for each index
        for (int i = 0; i < n; i++) {
            System.out.println(dist[i]);
        }
    }

}
