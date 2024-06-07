package datastructures.hackerrank.easy;

import java.util.Arrays;
import java.util.List;

public class AppleAnOranges {
//    Sam's house has an apple tree and an orange tree that yield an abundance of fruit. Using the information given below, determine the number of apples and oranges that land on Sam's house.
//
//    In the diagram below:
//
//    The red region denotes the house, where  is the start point, and  is the endpoint. The apple tree is to the left of the house, and the orange tree is to its right.
//    Assume the trees are located on a single point, where the apple tree is at point , and the orange tree is at point .
//    When a fruit falls from its tree, it lands  units of distance from its tree of origin along the -axis.
//    *A negative value of  means the fruit fell  units to the tree's left, and a positive value of  means it falls  units to the tree's right. *
//    Apple and orange(2).png
//
//    Given the value of  for  apples and  oranges, determine how many apples and oranges will fall on Sam's house (i.e., in the inclusive range )?
//
//    For example, Sam's house is between  and . The apple tree is located at  and the orange at .
//    There are  apples and  oranges. Apples are thrown  units distance from , and  units distance.
//    Adding each apple distance to the position of the tree, they land at .
//    Oranges land at . One apple and two oranges land in the inclusive range  so we print

    public static void main(String[] args) {
        int s=7;
        int t=10;
        int a=4;
        int b=12;
        List<Integer> apples = Arrays.asList(2, 3, -4);
        List<Integer> oranges = Arrays.asList(3, -2, -4);
        countApplesAndOranges(s,t,a,b,apples,oranges);
    }
    public static void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
        // Write your code here
        int appleCounter=0;
        int orangesCounter=0;
        for(int i=0;i<apples.size();i++)
        {
            int distance = a+apples.get(i);
            if(distance>=s && distance<=t)
            {
                appleCounter++;
            }
        }
        System.out.println("No of apples falling in the house are :: "+appleCounter);
        for(int i=0;i<oranges.size();i++)
        {
            int distance = b+oranges.get(i);
            if(distance>=s && distance<=t)
            {
                orangesCounter++;
            }
        }
        System.out.println("No of oranges  falling in the house are :: "+orangesCounter);
    }
}
