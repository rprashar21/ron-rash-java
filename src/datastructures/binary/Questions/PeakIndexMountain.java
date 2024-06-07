package datastructures.binary.Questions;

public class PeakIndexMountain {
    /*
    * bitonic array  increasing 10,20,30,40,50,4,3,2,1  and then dreasing
    *  so the peak of the mountain is the largest elemnt
    *  so the element which is the largest is greater than prev and next
    *
    * */
    public static void main(String[] args) {

        int[] array = new int[]{10,20,300,57,50,4,3,2,1};
        int [] a;
        a=new int[9];

        final int peakByBS = findPeakByBS(array);
        System.out.println(peakByBS);
    }

    private static int findPeakByBS(final int[] array) {

        int start=0;
        int end=array.length-1;

        // break condition when start > end
        // when start and end both point to same elenet we get the max
        while (start<end)
        {
            int mid = start +(end-start)/2;

            if(array[mid]>array[mid+1])
            {
                /*
                * you are in the descending part of the array ,
                * mid element might be the answer so make mid elemnt your end
                * */
                end=mid;
            }
            else
                // ur in the sacending part of the array
               // so we know mid+1 is greater than mid and cannot be the answer
                start=mid+1;

        }
        //
         return array[start];
    }
}
