package datastructures.leetcode.sortingQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortColors {

    // this is based on QuickSrt Algo
    // QuickSort is based on partition

    //

    public static void main(String[] args) {

        int a[] = new int[]{2,0,2,1,1,0};
        // in this array we only have 1,0,2 // hence we need to find inplace without any extra space

        // brute force
        // find 3 bucktes and place 0,1 and 2 with their cunst and then place all the 0,1,2

        // for the optimum solution have three pointer l m and right
        // swap 2 in rigth and o in left and skip for 1
        //{2,0,2,1,1,0}; l=0,m=0,r=a.len-1 -- 0,0,1,1,2,2 ---

      //  bruteForce(a);
        bruteForceUsingQueue(a);
        optimumSolution(a);
        optimumSolutionNew(a);

        for(int i=0;i<10;i++)
        {

        }

    }

    private static void optimumSolutionNew(final int[] a) {
        int left =0;
        int right = a.length-1;
        int mid =0;

        while(mid<=right){
         //


        }
    }

    private static void bruteForceUsingQueue(final int[] a) {

        // loop thru the array and check for 0 and then 1 and then 2
        // we will have 3 queue
    }

    private static void optimumSolution(final int[] a) {
        int l=0;
        int m=0;
        int r=a.length-1;
        while(m<=r)
        {
            if(a[m]==0)
            {
                swap(a,m,l);
                l++;
                m++;
            }
            else if(a[m]==1){
                m++;
            }
            else {
                swap(a,m,r);
                r--;
            }
        }
        System.out.println(Arrays.toString(a));
    }

    private static void swap(int []a, int m,  int r) {
        int temp =a[m];
        a[m]=a[r];
        a[r]=temp;
    }

    private static void bruteForce(final int[] a) {

        // new ARRAY --
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i< a.length;i++)
        {
            if(map.containsKey(a[i]))
            {
                map.put(a[i],(map.get(a[i])+1));
            }
            else {
                map.put(a[i],1);
            }
        }

        // first put all o and the t
        int k=0;
        while(map.get(0)>0)
        {
            a[k++]=0;
            map.put(0,map.get(0)-1);
        }
        // put 1s
        while(map.get(1)>0)
        {
            a[k++]=1;
            map.put(1,map.get(1)-1);
        }
        // put 2s
        while(map.get(2)>0)
        {
            a[k++]=2;
            map.put(2,map.get(2)-1);
        }
        System.out.println(Arrays.toString(a));
    }


}
