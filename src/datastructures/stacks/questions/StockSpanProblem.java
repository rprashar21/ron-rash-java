package datastructures.stacks.questions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

public class StockSpanProblem {


  //  int a[] = new int[]  [100,80,60,70,75,85];
    // stock span problem = next greater element to right at
                   //        0  1   2 3  4  5
    //int a[]= new int[]    {100,80,60,70,75,85}; //
  //  next greatest index    0, 0, 1, 1,  1,0   {currentIndex -resultIndex }
     // output       -->   1, 1   1  2   3 5   -- reuslts no of stocks less= the current daya stcok
    // ata any given place stock find all the stock prices which are less than or equal to that amaount +1


    // we need to see the left side of the array

    // brute force

    public static void main(String[] args) {
        int a[]= new int[]    {100,80,60,70,75,85};
        bruteForce(a); // complextity is O(n2) -- reduce it to On

        // we will solve using nextGreatestElemntToLeft an

        nearestGreatestElement(a);
      //  nearestGreaterToLetftUsingMap(a);
        // in the stack we store the pair <value and index of the array element>
    }

    private static void nearestGreatestElement(final int[] a) {
        Deque<Pair> stack = new ArrayDeque<>();
        // result array will have the indexes [] pair has ngl and index
        int[] resultArray = new int[a.length];
        for(int i=0;i<a.length;i++)
        {
            if(stack.isEmpty())
            {
                // push the elemnt and its index
                resultArray[i]=-1;
            } else if (stack.peek().key>a[i]) {
                resultArray[i]=stack.peek().value;
            } else if (stack.peek().key<a[i]) {
                while (!stack.isEmpty() && stack.peek().key<a[i])
                {
                    stack.pop();
                }
                if(stack.isEmpty())
                {
                    resultArray[i]=-1;
                }
                else {
                    resultArray[i]=stack.peek().value;
                }

            }

            stack.push(new Pair(a[i],i));
        }
        // for final asnwer will will subtract the index with result array index
        System.out.print(Arrays.toString(resultArray));
        for(int i=0;i<a.length;i++)
        {
            int value =i-resultArray[i];
            System.out.print(value+", ");
        }
        System.out.print("]");
    }
    private static void nearestGreaterToLetftUsingMap(final int[] a){
        // loop thru the array for each element put the next greatest in  map say
        //{100,  80,    60,   70,   75,   85} --> have a temp array whihc will bascially store all ngi


        // final answer will be subtracting these values from  temp array   with current index
        // iterate thru map an put in the output array [Math.abs(map.getkey - map.getVaue)]
        Stack<Pair> stack = new Stack<>();
        int[] temp = new int[a.length];
        int k=0;
        for(int i=0;i<a.length;i++){

            if(stack.isEmpty())
            {
                temp[k++]=-1;
            } else if (a[i]<stack.peek().key) {
                temp[k++]=stack.peek().value;
            } else if (a[i]>stack.peek().key) {
                while(!stack.isEmpty() && a[i]>stack.peek().key)
                {
                    stack.pop();
                }
                if(stack.isEmpty())
                {
                    temp[k++]=-1;
                }
                else
                {
                    temp[k++]=stack.peek().value;
                }

            }
           stack.push(new Pair(a[i],i));
        }
        System.out.println(Arrays.toString(temp));

        // now subtract temp array values with current index
        int [] output = new int[temp.length];
        for(int i=0;i<a.length;i++){
            output[i]= Math.abs(i-temp[i]);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(output));
    }

    private static void bruteForce(final int[] a) {
        //
        int[] resultArray = new int[a.length];
        resultArray[0]=1;
        for(int i=1;i<a.length;i++)
        {
            int count=1;
            for(int j=i-1;j>=0;j--)
            {
                if(a[j]<a[i])
                {
                    count++;
                }
                resultArray[i]=count;
            }
        }
        System.out.println(Arrays.toString(resultArray));
    }
}


class Pair{
    int key;
    int value;

    public Pair(int key,int value){
        this.key = key;
        this.value = value;
    }
}
