package datastructures.sorting.questions;

public class DuplicateNumber {

    /*
    Question
    * array is given -- range is give from 1-n sequential numbers
     one repeated number -- return this repeated number
    *
    do not modify the array and solve using constant space
           0,1,2,3,4
      a-  {1,3,4,2,2}-- cyclic sort , 1,4,3,2,2 --1,2,3,4,2

        doing cyclic sort will put elements in the correct position and the one
        which is not at the correct position will the duplicate element
    * */

    public static void main(String[] args) {
        int[] a ={1,3,4,2,2};
        // do cyclic sort

        final int duplicateNumber = findDuplicateNumber(a);
        System.out.println(duplicateNumber);
    }

    private static int findDuplicateNumber(final int[] a) {
        int i=0;
        while(i<a.length)
        {
            int correctIndex = a[i]-1;
            if(a[i]!=a[correctIndex])
                swap(a,i,correctIndex);
            else
                i++;
        }
        // find the duplicate element
        for(int j=0;j<a.length;j++)
        {
            //element which is not at ist position
            if(j!=a[j]-1)
                return a[j];
        }
        return -1;
    }

    private static void swap(final int[] a, final int i, final int correctIndex) {
        int temp=a[i];
        a[i]=a[correctIndex];
        a[correctIndex]=temp;
    }
}
