package datastructures.sorting.questions;

public class DuplicateNumberCopy {
    public static void main(String[] args) {
        int[] a = {1, 3, 4, 2, 2}; // {1 }
        // do cyclic sort

        final int duplicateNumber = findDuplicateNumber(a);
        System.out.println(duplicateNumber);
    }

    private static int findDuplicateNumber(final int[] a) {


        int i = 0;
        while (i < a.length) {
            if (a[i] != i + 1) {
                int correctIndex = a[i] - 1;
                if (a[i] != a[correctIndex]) // checks for correct position
                    swap(a, i, correctIndex);
                else
                    return a[i];
            } else
                i++;
        }
        return -1;
    }

    private static void swap(final int[] a, final int i, final int correctIndex) {
        int temp = a[i];
        a[i] = a[correctIndex];
        a[correctIndex] = temp;
    }
}
