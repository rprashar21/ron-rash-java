package datastructures.arrays.arrayQuestionsleetcode;

import java.util.Arrays;

public class FlowerPots {
    public static void main(String[] args) {


        // a[1,0,0,0,1] --> [1,0,1,0,1] --returns true
        // need to place the flowers in adjacent places
        // reduce the count from the number while doing the loop and if the
        int[] a = new int[]{0,0,0};
        int basket=1;
        System.out.println( placeFlowerPots(a,basket));

        // puedo code
        /// loop thru this and check prev and next if they are zero or not
        // also check if prev is array starting amd next is array ending [1,0,0,0,0,1]
        // pointers are diff move one ahead else check if the pointer is at start postion or end postion
    }

    private static boolean placeFlowerPots(final int[] a,final int n) {

        int count =0;
        if(a==null ||  a.length<=1)
            return  false;

        for(int i=0;i<a.length;i++)
        {
            if(a[i]==0) {
                int prev = (i==0 || a[i-1]==0)?0:1;
                int next=(i==a.length-1 || a[i+1]==0)?0:1;

                if (prev == 0 && next == 0)
                {
                     a[i]=1;
                     count++;
                }
            }
        }
        System.out.println(Arrays.toString(a));
        return count>=n;
    }


    static boolean placeFlowerPotsnew(int[] a , int n)
    {
        // need to check whether the flower post are placed in correct position or not

return false;
    }
}
