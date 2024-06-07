package datastructures.hackerrank.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiagnolArray {

    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,2,3));
        list.add(Arrays.asList(4,5,6));
        list.add(Arrays.asList(9,8,9));
        diagonalDifference(list);

        sum();
    }

    public static int diagonalDifference(List<List<Integer>> list) {
        // Write your code here
        int leftSum =0;
        int rightSum=0;
        for(int row=0;row<list.size();row++){
            for(int col=0;col<list.get(row).size();col++){
               // this is how we print all the values
                


            }
        }
        int diff = rightSum - leftSum;
        System.out.println("left"+leftSum+" "+rightSum);
        return Math.abs(diff);
    }

    static void sum(){
        List<List<Integer>> twoDList = new ArrayList<>();

// populate the 2D list with some data
        twoDList.add(Arrays.asList(1, 2, 3));
        twoDList.add(Arrays.asList(4, 10, 6));
        twoDList.add(Arrays.asList(7, 8, 2));

        int sum1 = 0; // sum of diagonal from top-left to bottom-right
        int sum2 = 0; // sum of diagonal from top-right to bottom-left

// calculate the sum of the diagonals
        for (int i = 0; i < twoDList.size(); i++) {
            sum1 = sum1 + twoDList.get(i).get(i); // add the element on the diagonal from top-left to bottom-right
            sum2 += twoDList.get(i).get(twoDList.size() - i - 1); // add the element on the diagonal from top-right to bottom-left
        }

// print the sum of the diagonals
        System.out.println("Sum of diagonal from top-left to bottom-right: " + sum1);
        System.out.println("Sum of diagonal from top-right to bottom-left: " + sum2);

    }

}
