package datastructures.arrays.Questions;

public class findMaxInArray {
    public static void main(String[] args) {
        int[] arr = {1,3,200,9,23}; // 23,9,2,31,1

        final int max = findMax(arr);
        System.out.println("max value : "+max);
    }

    private static int findMax(final int[] arr) {
        int max = arr[0];

        for (int i=0;i<arr.length;i++)
        {
            if(arr[i]>max)
                max=arr[i];
        }
        return max;
    }
}
