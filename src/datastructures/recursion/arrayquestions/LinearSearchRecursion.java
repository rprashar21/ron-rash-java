package datastructures.recursion.arrayquestions;

import java.util.ArrayList;

public class LinearSearchRecursion {

    public static void main(String[] args) {

      //  int[] a = new int[]{10,2,3,3,7,8,3,9,3};
        int[] a = new int[]{1,2,3};


        System.out.println( linearSearch(a,3,0));

        // return the index or -1
        System.out.println(linearSearch2(a,10,0));

        // find indices of suppose repeating elements like if the array is [{10,2,3,6,6,8}]
        // retrun an array list === keep adding the eleents
        // so the return type will be a list and hence we can passs that list in the argument
        ArrayList<Integer> integers = new ArrayList<>();
        linearSearchMultiple(a,3,0,integers);
        System.out.println(integers);
    }

    private static ArrayList<Integer> linearSearchMultiple(final int[] a, int target,  int index,ArrayList<Integer> list) {

     if(index==a.length)
         return list;

     if(a[index]==target)
         list.add(index);

     return linearSearchMultiple(a,target,index+1,list);
    }

    private static boolean linearSearch(final int[] a, final int target,int index) {
        // base
        if(index==a.length)
            return false;

        if (a[index]==target)
            return true;

        return linearSearch(a,target,index+1);
    }

    private static int linearSearch2(final int[] a, final int target,int index) {
        // base
        if(index==a.length)
            return -1;

        if (a[index]==target)
            return index;

        return linearSearch2(a,target,++index);
    }
}
