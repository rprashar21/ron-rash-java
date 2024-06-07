package datastructures.binary.Questions;

import java.util.Arrays;

public class RotateArray {

    // rotate an array upt k times

    /*
    * algo // reverse an array
    *         reverse upto k elements
    *         rever from k+1 to end
    * */

    public static void main(String[] args) {

        int[] arr= {1,2,3,4,5}; // {5,6,7,1,2,3,4}
        int k=3; // no of time to be rotated
//        reverse(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
//        reverse(arr,0,k-1);
//        System.out.println(Arrays.toString(arr));
//        reverse(arr,k, arr.length-1);
//
//        System.out.println(Arrays.toString(arr));

        rotateAnArrayKTimes(arr,3);
    }

    private static void rotateAnArrayKTimes(final int[] a,  int k) {

        // reverse the array -- [1,2,3,4,5,6,7] --[7,6,5,4,3,2,1]
        // revers the arry till k-1 --[5,6,7....]
        // rever the array again frm k - a.lenth-1 [...1,2,3,4]
        // final array will be like [5,6,7,1,2,3,4]

        int len = a.length;
        if(k>len)
        {
            k= k%len;
        }

        reverseNewWay(a,0,a.length-1);
        reverseNewWay(a,0,k-1);
        reverseNewWay(a,k,a.length-1);
        System.out.println(Arrays.toString(a));

    }

    private static void reverseNewWay(final int[] a,  int start,  int end) {
        while(start<=end)
        {
            int temp = a[start];
            a[start]=a[end];
            a[end]=temp;
            start++;
            end--;
        }
    }

    private static void reverse(final int[] array,  int start,  int end) {
       while(start<=end)
       {
           int temp = array[start];
           array[start]=array[end];
           array[end]=temp;
           start++;
           end--;

       }
    }




}
