package datastructures.arrays.Questions;

import java.util.Arrays;

public class DeleteElementsInArray {
    public static void main(String[] args) {

        // delete at beigining
        int[] a = {10,2,3,4};
        int target =90;
        int position=3;
//        insertAtStart(a,target);
        deleteAtAnyPosution(a,target,0);
        deleteAtAnyPosution(a,target,1);
        deleteAtAnyPosution(a,target,2);
        deleteAtAnyPosution(a,target,3);
        deleteAtAnyPosution(a,target,4);
    }

    private static void deleteAtAnyPosution(final int[] a, final int target, final int position) {
        if(position>a.length)
            throw new ArrayIndexOutOfBoundsException("position is greater than array");

        int[] temp = new int[a.length-1];
        for(int i=0;i<position;i++)
        {
            temp[i]=a[i];
        }
        for(int i=position+1;i<a.length;i++)
        {
            temp[i-1]=a[i];
        }
        System.out.println(Arrays.toString(temp));
    }

    private static void insertAtStart(final int[] a, final int target) {
        //start from the end
        // shift alelemt till 1st index
        // and pt the target elemnt in the 0th postion
    }

    private static void dele(final int[] a, final int target,final int position) {
        // start from the end
        // shift alelemt till postion index index
        // and pt the target elemnt in the  postion
    }
}
