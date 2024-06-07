package datastructures.arrays.hackerrank;

import java.util.Arrays;

public class LeftAndRightRotate {

    private static final int rotations = 4;

    public static void main(String[] args) {

        int a[] = {1, 2, 3, 4, 5, 6}; // basically a circular array [5,6,1,2,3,4]

        // if no of rotatons = length of the array thne all the elemenst will be back to their position
      //  leftRotate(a);
        rightRotate(a);
    }

    private static void rightRotate(final int[] a) {
        int len =a.length;
        reverse(a,0,len-1);
        reverse(a,0,rotations-1);
        reverse(a,rotations,len-1);
        System.out.println(Arrays.toString(a));
    }

    private static void leftRotate(final int[] a) {

        // first check that after the final result how the array will look
        // so [1,2,3,4,5,6] left rotate 4 time will be [5,6,1,2,3,4]
        // now reverse the orginal array [6,5,4,3,2,1] -- now comapre with final result and see from where to where we have to reverse
        // revers [6,5,4,3,2,1] from 0 to
        int len =a.length;
        reverse(a,0,len-1);
        reverse(a,0,(len-1)-rotations);
        reverse(a,(len-1)-rotations+1,len-1);
        System.out.println(Arrays.toString(a));
    }


    private static void reverse(final int[] a,int start,int end) {
      while(start<=end)
      {
          int temp =a[start];
          a[start]=a[end];
          a[end]=temp;
          start++;
          end--;
      }
    }

}
