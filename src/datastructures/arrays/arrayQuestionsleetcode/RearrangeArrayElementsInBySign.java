package datastructures.arrays.arrayQuestionsleetcode;

import java.util.Arrays;

public class RearrangeArrayElementsInBySign
{
    public static void main(String[] args) {
        int [] a = {3,1,-2,-5,2,-4};

        // basically equal positive and negatove elemenst and arrange them in odrer
        // like + and -  {3,-2,1,-5,2,-4} one positive and one equal

        arrange(a);
    }

    private static void arrange(final int[] a) {
        int[] result = new int[a.length];

        // positve nos are on positive index and negatove at odd places
        int positiveIndex = 0;
        int negativeIndex = 1;
        for(int i=0;i< a.length;i++)
        {
            if (a[i] > 0) {
                // positive no put in positive index
                result[positiveIndex] = a[i];
                positiveIndex=positiveIndex+2;
            }
            else{
                result[negativeIndex] = a[i];
                negativeIndex=negativeIndex+2;
            }
        }
        System.out.println(Arrays.toString(result));
    }
}
