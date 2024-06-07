package datastructures.binary;

public class BInarySearch4 {
    public static void main(String[] args) {
        // floor question
        // floor is the greatest which is smaller than the target

        // 12 13 14 15 16  19
        // tagret - 18 -- floor of 18 is 16 --

        // if the



        final int[] array = new int[]{10,20,30,40,50,60,70,80,90,100}; //10,20,30,40,50,60,70,80,90,100
        final int target = 35;
        final int index = floorBS(array, target);
        System.out.printf("next closet number greater than but less than target is  %d%n", index);
    }

    private static int floorBS(final int[] array, final int target) {

        /*
        * logic
        * when while loop breaks and we are not able to find the number -- so next or closest number to 35 is 30
        * end = start-1 -- therefore end is the answer
        * */

        int start=0;
        int end =array.length-1;

        // check if the array is ascending or descending
        boolean isAscending = array[start]< array[end];
        while (start<=end)
        {
            int mid = start + (end-start)/2;

            if(array[mid]==target)
                return mid;
            if(isAscending)
            {
                if(target > array[mid])
                start= mid+1;
                else
                 end=mid-1;
            }
            else
            {
                if(target > array[mid])
                    start=mid-1;
                else
                    end=mid+1;
            }
        }
        return end;
    }
}
