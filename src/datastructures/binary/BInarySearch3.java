package datastructures.binary;

public class BInarySearch3 {
    public static void main(String[] args) {
        // find the next greater number
        // cieling question
        // cieling is the smallest number greater than target

        // 12 13 14 15
        // tagret - 13 -- ceiling is 14
        final int[] array = new int[]{12,13,14,15,16,17}; //10,20,30,40,50,60,70,80,90,100
        final int[] a = new int[]{12,13,14,15,16,17}; //10,20,30,40,50,60,70,80,90,100
        System.out.printf("next smallest number greater than the target is  %d%n", ceilingBinarySearch(a,17));
    }


    // gices us the next greatest element
    private static  int ceilingBinarySearch(final int[] a, final int target)
    {
        int start = 0;
        int end =a.length-1;

        while(start<=end)
        {
            int mid = start +(end-start)/2;

            if(target>=a[mid])
            {
                start=mid+1;
            }
            else {
                end=mid-1;
            }
        }
         return start;
    }



}
