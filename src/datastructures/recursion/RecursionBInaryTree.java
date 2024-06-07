package datastructures.recursion;

public class RecursionBInaryTree {
    /*
     * Recursion is basically done with dynamic programming to reduce the duplicate work
     *
     * In Recursion 3 things are important
     *  1. Arguments to pass
     *  2. return type.
     *  3. what argument should go in the body
     * */
    public static void main(String[] args) {

        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,20,30,40,50,60,70,80,90};
        int target = 2;
        final int search = recursiveBinarySearch(a, target, 0, a.length - 1);
        System.out.println(search);

    }

    private static int recursiveBinarySearch(final int[] a, final int target, int start, int end) {


        if (start > end)
            return -1;

        int mid = start + (end - start) / 2;

        if (target == a[mid])
            return mid;

        if (target > a[mid]) {
            return recursiveBinarySearch(a, target, mid + 1, end);
        }
        return recursiveBinarySearch(a, target, start, mid - 1);
    }
}
