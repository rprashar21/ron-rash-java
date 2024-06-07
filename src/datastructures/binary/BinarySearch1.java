package datastructures.binary;

public class BinarySearch1 {
    public static void main(String[] args) {

        final int[] array = new int[]{-1, 0, 2, 4, 19, 20, 36, 77, 88, 99, 200};
        final int target = 800;
        final int index = binarySearch(array, target);
        System.out.printf("searched element is at position %d%n", index);
    }

    // return an index of the search element
    // return -1 if the element is not present
    static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
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
