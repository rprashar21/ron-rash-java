package datastructures.hackerrank.easy;

import java.util.Arrays;
import java.util.List;

public class MInMax {
    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(396285104,573261094,759641832,819230764,364801279);
        miniMaxSum(integers);
        miniMaxSum1(integers);
    }

    public static void miniMaxSum(List<Integer> list) {
        // Write your code here
        long min = findMin(list);
        long max = findMAx(list);

        long minSum=0;
        long maxSum=0;
        System.out.println(min);
        System.out.println(max);
        for(int i =0;i<list.size();i++)
        {
            if(list.get(i)!=max )
            {
                minSum=minSum+list.get(i);
            }
            if(list.get(i)!=min)
            {
                maxSum=maxSum+list.get(i);
            }
        }
        System.out.print(minSum+" "+maxSum);
    }

    private static long findMin(List<Integer> list)
    {
        long min =list.get(0);
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)<min)
            {
                min= list.get(i);
            }
        }
        return min;
    }
    private static long findMAx(List<Integer> list)
    {
        long max =list.get(0);
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)>max)
            {
                max= list.get(i);
            }
        }
        return max;
    }

    public static void miniMaxSum1(List<Integer> list) {
        // Write your code here

        // sort a list
        for(int i=0;i<=list.size()-2;i++)
        {
            for(int j=i+1;j>0;j--)
            {
                if(list.get(j)<list.get(j-1))
                {
                    int temp = list.get(j);
                    list.set(j, list.get(j-1));
                    list.set(j-1, temp);
                }
            }
        }
        System.out.println(list);
        long minsum=0;
        long maxsum=0;
        for(int i=0;i<list.size()-1;i++)
        {
            minsum=minsum+list.get(i);
        }
        for(int i=1;i<list.size();i++)
        {
            maxsum=maxsum+list.get(i);
        }
        System.out.print(minsum+" "+maxsum);
    }
}
