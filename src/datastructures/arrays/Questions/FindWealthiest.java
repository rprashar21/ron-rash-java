package datastructures.arrays.Questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindWealthiest {

    /*
    * Find wealth
    * find the max in a 2d array
    * */



    public static void main(String[] args) {
        int[][] a = {
                {10,30,200} //240
                ,{1,1,1}   // 3
                ,{7,7,70}  // 84
        };

        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(10,30,200));
        list.add(Arrays.asList(10,0,200));
        list.add(Arrays.asList(7,3,200));

        findMaxValue(a);
        int maxValue = findMaxValue(list);
        System.out.println(maxValue);
        System.out.println(findMaxValue(a));
    }

    private static int findMaxValue(final List<List<Integer>> list) {
        int maxValue = Integer.MIN_VALUE;
        for(int i=0;i<list.size();i++)
        {
            int sum = 0;
            for(int j=0;j<list.get(i).size();j++)
            {
                sum+=list.get(i).get(j);
            }

            maxValue = Math.max(maxValue,sum);
        }
        return maxValue;
    }

    private static int findMaxValue(final int[][] a) {

        int maxvalue = Integer.MIN_VALUE;
        for(int row=0;row<a.length;row++)
        {
            int accountBalance=0;
            for(int col=0;col<a[row].length;col++)
            {
                accountBalance+=a[row][col];
            }

          if(accountBalance>maxvalue)
          maxvalue=accountBalance;
        }
            //

         return maxvalue;
    }
}
