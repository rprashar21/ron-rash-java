package datastructures.leetcode;

import java.util.Arrays;

public class SecondLargest {
    public static void main(String[] args) {
        int []a ={12,34,22,10,18};
//        secondLargest(a);

    // Algo take 2 pointer largest and second
    // initilize both with some less value
    // if a[i]>firstlargest --> the first put first value to second
    // and then put a[i] on first

    // now when a[i] is not greater than first --
        // then check whether that value is in between largest and second if yes replace secondlargest with current a[i]
        System.out.println(secondLargestNew(a));
    }

    private static int secondLargestNew(final int[] a)
    {
        int largest =Integer.MIN_VALUE;
        int secondLargest =Integer.MIN_VALUE;

        for(int  i=0;i<a.length;i++)
        {
            if(a[i]>largest)
            {
                secondLargest=largest;
                largest=a[i];
            }
            // now when a[i] is not greater than first --
            // then check whether that value is in between largest and second if yes replace secondlargest with current a[i]
            else if (a[i]>secondLargest && a[i]<largest) {
                secondLargest=a[i];
            }
        }
        return secondLargest;
    }

    private static int secondLargest(final int[] a) {


        // o(n)
        if(a.length<2)
            return 0;



        int i=0,second;
        int first = second = Integer.MIN_VALUE;
        for ( i = 0; i < a.length; i++) {
            /* If current element is greater than
            first then update both first and second */
            if (a[i] > first) {
                second = first;
                first = a[i];
            }

            /* If arr[i] is in between first and
               second then update second  */
            else if (a[i] > second && a[i] != first)
                second = a[i];
        }
        return second;
    }
}
