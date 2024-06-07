package datastructures.binary.Questions;

public class SearchInSortedRotatedArray {
    public static void main(String[] args) {
        // question
        // sorted array whihc is rotated -- find an elemnet
        // [3,4,5,6,7,0,1,2] target = 5 .. ans -2
        // if element not found return -1
        //

        /*
         * Algo for this is
         * find the pivot element        3,4,5,6,7,0,1,2
         * apply binary search bs(array,target,start,pivot)-- if found return
         * else bs(array,target,pivot+1,end)
         *
         * */

        // int[] array = new int[]{3,4,5,6,7,0,1,2};
        int[] array = new int[]{7, 0, 1, 2, 3, 4, 5, 6};
        int target = 5;
        final int pivot = pivot(array);
        int ans=-1;
        if(pivot==-1)
            ans= bs(array,target,0,array.length-1);

        // case 1 -- if pivot is found then we have 2 asc sorted arrsys
        if(target==array[pivot])
            ans=pivot;

        // case 2
        if(array[0]<target)
            ans=bs(array,target,0,pivot-1);

        else if(array[0]>target)
            ans=bs(array,target,pivot+1,array.length-1);

        System.out.println(ans);
    }


    private static int pivot(final int[] array) {

        // case 1 - find mid
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            // case1 a[mid]>a[mid+1]
            if (mid < end && array[mid] > array[mid + 1])
                return mid;
                //case 2 where a[mid]<a[mid-1]
            else if (mid > start && array[mid] < array[mid - 1]) {
                return mid - 1;
            }
            // case 3 start<=a[mid]
            else if (array[start] < array[mid]) {
                start = mid + 1;
            }
            // case 4 start>a[mid]
            else
                end = mid - 1;
        }
        return -1;
    }

    private static int bs(final int[] arr, final int target, int start, int end) {

        // run this process until start<=end
        while (start <= end) {
            // find the middle element
            int mid = start + (end - start) / 2; // might be possible that the start+end value be greater than integer value

            // check
            if (arr[mid] == target)
                return mid;
            else if (target > arr[mid])
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
}
