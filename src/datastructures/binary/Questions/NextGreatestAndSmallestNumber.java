package datastructures.binary.Questions;

public class NextGreatestAndSmallestNumber {

    public static void main(String[] args) {

        int [] a = new int[]{10,20,30,40,50,60,70,80,90};

        // find the next element greater than target --

        int start =0;
        int end =a.length-1;
        int target = 20;

        nextGreatestElement(a, start, end, target);
        nextSmallestElement(a,start,end,target);
    }

    private static void nextSmallestElement(final int[] a,  int start,
                                            int end, int target) {
        while(start <= end)
        {
            int mid = start +(end - start)/2;

            if(target > a[mid])
            {
                start =mid+1;
            }
            else {
                end =mid-1;
            }
        }
        System.out.println(end <= a.length-1? end :-1);
    }

    private static void nextGreatestElement(final int[] a, int start, int end, final int target) {
        while(start <= end)
        {
            int mid = start +(end - start)/2;

            if(target >= a[mid])
            {
                start =mid+1;
            }
            else {
                end =mid-1;
            }
        }
        System.out.println(start <= a.length-1? start :-1);
    }
}
