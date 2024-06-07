package datastructures.stacks.questions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxAreaHistogram {



    // hieght of buildings are given
    // need to find the width
    // for any building find the nsr and nsl , sub them and get their width
    // resulting array ie the width * length whihc is the value of the array and then find max

    public static void main(String[] args) {


        int[] a = new int[]{6,2,5,4,5,1,6};
        int[] width = new int[a.length];

        // loop thru this and find nsr and nsl


        int[] nearestSmallestToRight = nearestSmallestToRight(a);

        System.out.println("nearest smalles to right"+Arrays.toString(nearestSmallestToRight));

        int[] nearestSmallestToleft = nearestSmallestToleft(a);
        System.out.println("nearest smalles to ;etf"+Arrays.toString(nearestSmallestToleft));
        int k=0;
        for(int i=0;i<nearestSmallestToRight.length;i++)
        {
            width[k++]= (nearestSmallestToRight[i] - nearestSmallestToleft[i])-1;
        }
        System.out.println(Arrays.toString(width));

        // max in an array --fasterst way

        // loop thru both the length and width multiply them and find the max

    }

    private static int[] nearestSmallestToRight(int [] a) {
        int[] result = new int[a.length];
        Deque<Pair> stack = new ArrayDeque<>();
        int psuedoIndex =a.length;
        int k=a.length-1;
        for(int i=a.length-1;i>=0;i--)
        {
            if(stack.isEmpty())
            {
                result[k--]=psuedoIndex;
            }
            else if (a[i]>stack.peek().key) {
                result[k--]= stack.peek().value;
            } else if (a[i]< stack.peek().key) {
                while(!stack.isEmpty()  && a[i]< stack.peek().key)
                  stack.pop();
                if(stack.isEmpty())
                    result[k--]=psuedoIndex;
                else
                    result[k--]= stack.peek().value;
            }

            stack.push(new Pair(a[i],i));
        }
        System.out.println(Arrays.toString(result));
       return result;
    }
    private static int[] nearestSmallestToleft(int [] a) {
        int[] result = new int[a.length];
        Deque<Pair> stack = new ArrayDeque<>();
        int psuedoIndex =-1;
        int k=0;
        for(int i=0;i<a.length;i++)
        {
            if(stack.isEmpty())
            {
                result[k++]=psuedoIndex;
            }
            else if (a[i]>stack.peek().key) {
                result[k++]= stack.peek().value;
            } else if (a[i]< stack.peek().key) {
                while(!stack.isEmpty()  && a[i]<stack.peek().key)
                    stack.pop();
                if(stack.isEmpty())
                    result[k++]=psuedoIndex;
                else
                    result[k++]= stack.peek().value;
            }

            stack.push(new Pair(a[i],i));
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

//    private static int[] nearestSmallestToRight(final int[] a) {
//
//    }
}