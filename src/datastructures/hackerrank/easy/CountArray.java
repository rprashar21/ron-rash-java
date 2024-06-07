package datastructures.hackerrank.easy;

import java.util.Arrays;

public class CountArray {

    public static void main(String[] args) {
        int[] array = new int[]{1,1,3,2,1};
        countingSort(array);
    }
    public static void countingSort(int[] array) {
        int max = Arrays.stream(array).max().orElse(0);
        int[] count = new int[max + 1];
        int[] output = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            count[array[i]]++;//>> count[1] [0,3,1,1]
        }
        System.out.println(Arrays.toString(count));

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1]; // count[i] =count[i]+count[i-1]
        }
        System.out.println(Arrays.toString(count));
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }
        System.out.println(Arrays.toString(output));
        System.arraycopy(output, 0, array, 0, array.length);
        System.out.println(Arrays.toString(output));
    }

}
