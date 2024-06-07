package datastructures.stacks.hacckerank;

import java.util.ArrayList;
import java.util.List;

public class GameOf2Stacks {

    public static void main(String[] args) {
       Integer[] array ={4, 2, 4, 6, 1} ;
       Integer[] arr ={20, 1, 8, 5};

       // find the max no of elemnts u can pop suc that total sum is less than maxsum.
        ArrayList<Integer> a = new ArrayList<>(List.of(array));
        ArrayList<Integer> b = new ArrayList<>(List.of(arr));

        System.out.println(practiceSoultion(7,a,b));
        System.out.println(actual(7,a,b));
      //  https://www.youtube.com/watch?v=WMmST9al0DE&t=2s video solution

        int[] stack1 = {4, 2, 4, 6, 1};
        int[] stack2 = {20, 1, 8, 5};
        int x = 10; // Maximum sum constraint
    }

    public static int practiceSoultion(int maxSum, List<Integer> a, List<Integer> b) {

        int csum=0; // current Sum
        int maxCounter=0;
        int c1=0; // counter1
        int c2 =0; // counter2
        for(int elem : a)
        {

            if(csum+elem>maxSum)
            {
                break;
            }

            csum=csum+elem;
            c1++;
        }
      maxCounter=c1;

        // loop thru the second list and check
        for(int elem : b)
        {
            csum = elem+csum;
            c2++;
            while(csum>maxSum && c1>0)
            {
                csum=csum-a.get(c1-1);
                c1 =c1-1;
            }
           if(csum<=maxSum)
           {
               maxCounter = Math.max(c1 + c2, maxCounter);
           }
        }
        return maxCounter;
    }

    public static int actual(int maxSum, List<Integer> a, List<Integer> b) {
        int currentSum = 0; // current Sum
        int maxCounter = 0;
        int counter1 = 0; // counter for list a
        int counter2 = 0; // counter for list b

        // Process elements from the first list
        for (int elem : a) {
            if (currentSum + elem > maxSum) {
                break;
            }
            currentSum += elem;
            counter1++;
        }
        maxCounter = counter1; // Update maxCounter with elements from the first list only

        // Process elements from the second list, adjust the first list as necessary
        for (int elem : b) {
            currentSum += elem;
            counter2++;
            while (currentSum > maxSum && counter1 > 0) {
                counter1--;
                currentSum -= a.get(counter1);
            }

            if (currentSum <= maxSum) {
                maxCounter = Math.max(counter1 + counter2, maxCounter);
            }
        }
        return maxCounter;
    }


}
