package datastructures.arrays.twopointerQuestions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MovingZeros {

    public static void main(String[] args) {
        // moving zeors to the ned of the aaray
        // a[0,1,0,3,12]
        // [1,0,0,3,12]---1,3,0,0,12
        // o/p --> [1,3,12,0,0]
        // cannot make another array

        // [0] -->[0]

        // two ways
        /*

        *   a[0,1,0,3,12] --> copy the non zero elements in a queue q-->[1,3,12]
        *  pop the queue elements back o array and replace last elements with zero
        *  time comlexity O(n)  space complexity O(n)
        * */

        int [] array = new int[]{0,1,0,3,12};

      //  movingZerosWithQueue(array);

        //2nd efficient method of moving zeros
        /*
        * take two pointers  left and right both pointing to zero
        *  if right pointer != 0 then copy right pointers value to left until the end
        *  finally from left pointer index to end of the array put all values to  zeros
        *
        * */
        movingZerosWithEfficieny(array);
      //  movingzers(array);
        System.out.println(Arrays.toString(array));


    }


    private static int[] movingZerosWithEfficieny(final int[] a) {

        if (a.length == 1)
            return a;

        int left = 0;
        for (int right = 0; right < a.length; right++) {
            if (a[right] != 0) {
                a[left] = a[right];
                left++;
            }
        }
        // from left index to end of array uodate all with zero
        for (int i = left; i < a.length; i++)
        {
            a[i]=0;
        }
        return a;
    }

    private static int[] movingZerosWithQueue(final int[] a) {

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<a.length;i++)
        {
            if(a[i]!=0)
            queue.add(a[i]);
        }
        // from queue copy back the non zero elements in the array and in the end fill up with all zeros
        int j=0;
        while(queue.size()!=0)
        {
            a[j]=queue.poll();
            j++;
        }
        for(int i=j;i<a.length;i++)
        {
            a[i]=0;
        }

            return a;
     }

    private static int [] movingzers(final int[] a)
    {
        // indexs
      int left =0;
      int right = a.length-1;

      while(left<right)
      {
          if(a[left]==0 && a[right]==0)
              right--;

          else if (a[left]==0 && a[right]!=0) {
                int temp = a[right];
                a[left]=temp;
                a[right]=0;
               left++;
               right--;
          }
          else if( (a[left]!=0 && a[right]!=0)) {
              left++;
          }
      }

      return a;
    }
}
