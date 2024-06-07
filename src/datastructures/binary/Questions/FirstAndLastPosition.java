package datastructures.binary.Questions;

import java.util.Arrays;

public class FirstAndLastPosition {

    // smallest letter greater than target
    // array is sorted in non dreasing order
    // retrn the  smallest character int the array that is larger than the target
    // cieling question
    /*
     * examples
     *
     * */

    public static void main(String[] args) {
        int[] array = new int[]{5, 7, 7, 7, 7, 8, 8, 10}; // answer is c
        int target = 7;
        int[] result = new int[2];

        // practice to find out first and last position
        firstAndLastPostion();

        int startIndex = search(array, target, true);
        if (startIndex != -1) {
            result[1] = search(array, target, false);
        }
        result[0] = startIndex;


        System.out.println(result[0] + " " + result[1]);
        /*
         * Solution approach
         * Same approach as cieling instead that we need to remove the equals condition because it wants the next greatest
         * we have to return the start % length of an aray
         * */
    }

    private static int search(final int[] array, final int target, final boolean firstIndex) {

        int start = 0;
        int end = array.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target > array[mid])
                start = mid + 1;
            else if (target < array[mid])
                end = mid - 1;
            else {
                ans = mid;
                if (firstIndex) {
                    end = mid - 1;

                } else
                    start = mid + 1;
            }
        }
        return ans;
    }

    private static void  firstAndLastPostion(){

        int a[] = new int[]{5, 7, 7, 7, 7, 8, 8, 10};
        // we need to fidn the first and the last poistion of 7
        // why bs bcox of sorted array

        int target =7; // when we find this s e

        // a[m]== target -- m postion can be the first value // base condition will  always be s<end



        // ffind the firstElement if the first elemt is found then find the second element
        int firstPosition = firstAndLastPostionByBinarySearch(a,true,target);
        int secondPosition = -1;
        if(firstPosition!=-1)
        {
            secondPosition= firstAndLastPostionByBinarySearch(a,false,target);
        }
    int[] finalArray = new int[]{firstPosition,secondPosition};
        System.out.println(Arrays.toString(finalArray));
    }

    private static int firstAndLastPostionByBinarySearch(final int[] a, final boolean firstPosition,final int target) {

        int start =0;
        int end = a.length-1;
        int position =-1;
        while(start<=end)
        {
            int mid = start +(end-start)/2;
            
            if(a[mid]==target){
                position=mid;
            }
            if(target>a[mid])
            {
                start=start+1;
            } else if (target<a[mid]) {
                end=mid-1;
            }
            else {
                if(firstPosition)
                {
                    end=mid-1;
                }
                else {
                    start=mid+1;
                }
            }
        }
        
        return position;
    }
}
