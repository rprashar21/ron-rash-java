package datastructures.sorting.questions;

public class MaximumConsecutiveCounts {
    public static void main(String[] args) {

        int[] a = new int[]{1,1,0,1,1,0,1,1,1};
        // max cons 1 is 3

        maxConsecutiveCount(a);
    }

    private static void maxConsecutiveCount(final int[] a) {

        int max=0;
        int counter=0;
        for(int i=0;i<a.length;i++)
        {
            if(a[i]==0)
            {
                max = Math.max(max,counter);
                counter=0;
            }
            else {
                counter++;
            }

        }
        System.out.println(counter);
    }
}
