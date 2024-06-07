package datastructures.arrays.twodimarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array2dExample {
    public static void main(String[] args) {

        // 2d arrays are nothing but array of array
        int[][] array = {
                {1,2,3,4}, // but an array
                {5,6},
                {7,8,9}
        };
        // print this array
        //
        for(int row=0;row <array.length;row++)
        {
           for(int col=0;col< array[row].length;col++)
               System.out.print(array[row][col]+ " ");

            System.out.println(" ");
        }

  // 2d list is also array of array
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,2,3));
        list.add(Arrays.asList(4,5,6));
        list.add(Arrays.asList(7,8,9));

        // iterate thru this array and give sum of diagnols
        System.out.println("Printing a 2d list");
        for(int i=0;i< list.size();i++)
        {
            for(int j=0;j<list.get(i).size();j++)
            {
                System.out.print(list.get(i).get(j)+" ");
            }
            System.out.println();
        }
        int diagnolSum=0;
        int reversediagnolSum=0;
        for(int i=0;i< list.size();i++)
        {
            diagnolSum=diagnolSum+list.get(i).get(1);
            reversediagnolSum=reversediagnolSum+list.get(i).get(list.size()-i-1);
        }
        System.out.println("diagnol sum is "+diagnolSum);
        System.out.println("reversediagnolSum sum is "+reversediagnolSum);
        // give me sum of diagnols
        int diagSum=0;
        for(int i=0;i<list.size();i++)
        {
            diagSum =diagSum+list.get(i).get(i);
        }
        System.out.println("diagnol sum is "+diagSum);
    }
}
