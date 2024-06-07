package datastructures.binary.Questions;

public class FindInRotatedSortedArrayWithDuplicates {


    public static void main(String[] args) {
        int[] a = {2, 9, 2, 2, 2};


        findInRotatedDuplicateArray(a);
    }

    private static void findInRotatedDuplicateArray(final int[] a) {

        int peak = pivot(a);
        System.out.println(peak);
    }

    private static int pivot(final int[] a) {

        //case1 -- > a[mid] >a[mid+1]
        // c
        int start = 0;
        int end = a.length - 1;

        while (start <= end) {
            //case1 -- > a[mid] >a[mid+1] //[3,4,5,6,7,0,1,2]
            int mid = start + (end - start) / 2;
            if (a[mid] > a[mid + 1])
                return mid;
            else if (a[mid] < a[mid - 1])
                return mid - 1;

            // case 2 when in the rotated array pivot is at the start [7,0,1,2,3,4,5,6]
            //{2, 9, 2, 2, 2};
            // with duplicate elements start mid and end all are equal
            // then ignore the duplicates but check if they are not pivot
            if (a[start] == a[mid] && a[mid] == a[end]) {
                if (a[start] > a[start + 1]) {
                    return start;
                }
                start++;
                if (a[end] < a[end - 1]) {
                    return end;
                }
                end--;
            } else if (a[start] < a[mid] || (a[start]==a[mid] && a[mid]>a[end]) ){
                start=mid+1;

            }
            else
                end=mid-1;

        }
        return -1;
    }
}
