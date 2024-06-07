package datastructures.slidingWindow.fixed;

import java.util.ArrayList;
import java.util.List;

public class FirstNegativeNumbers {
    public static void main(String[] args) {
        //find first negative numbers in a window size of k
        int[] a = {12, -1, -7, 8, -15, 30, 16, 28}; // output --> [-1,-1,,-7,-15]
        int k = 3;
        findNegative(a, k);
        findFirstNegative(a,k);
    }

    private static void findNegative(final int[] a, final int k) {
        int i = 0;
        int j = 0;
        List<Integer> tempList = new ArrayList<>();
        List<Integer> finalList = new ArrayList<>();


        while (j < a.length - 1) {
            if (a[j] < 0)
                tempList.add(a[j]);

            if(j-i+1<k)
                j++;

            else if (j-i+1==k) {

                if(!tempList.isEmpty())
                    finalList.add(tempList.get(0));

                if(a[i]== tempList.get(0))
                    tempList.remove(0);

                i++;
                j++;
            }

        }
        System.out.println(finalList);
    }
    private static void findFirstNegative(final int[] a, final int k){
        List<Integer> tempList = new ArrayList<>();
        List<Integer> finalList = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<a.length-k)
        {
            if(a[j]<0)
            tempList.add(a[j]);

            if(j-i+1==k)
            {
                if(!tempList.isEmpty())
                finalList.add(tempList.get(0));

                if(a[i]==tempList.get(0))
                 tempList.remove(0);

                i++;

            }
            j++;
        }
        System.out.println(finalList);
    }
}
