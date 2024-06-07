package datastructures.arrays.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftRotateArray {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int d=2;
        System.out.println(leftRotate(d,list));

        // rotate an array left
        int [] a = new int[]{1,2,3,4,5};

        // for left rotation by n elements
        // take a temp array
        // put elemenst from d to end of the array
        // put element from 0 to d

        leftRotateArray(d,a);

        // rotate an array by one positin
        // [1,2,3,4,5] after one left rotation [2,3,4,5,1] -- so store the value somewhere and then last main usko last index pe rakh do

        // [1,2,3,4,5] d = 2 ie no of left rotation
        // reverse(a,0 d-1)
        // revese (a,d,a.lenght)
        // reverse(a)
        int [] a1 = new int[]{1,2,3,4,5};
        leftRotateArrayViaRversal(5,a1);
    }

    private static void leftRotateArrayViaRversal( int d, final int[] a) {

        System.out.println("reversal ");
        int length = a.length;
        if(d>a.length)
        {
            d=d% (length);
        }


        // reverse array from 0 - d-1
        reverse(a,0,d-1);
        reverse(a,d,a.length-1);
        reverse(a,0, a.length-1);

        System.out.println(Arrays.toString(a));
    }

    private static void reverse(final int[] a,  int start,  int end) {

        while(start<=end)
        {
            int temp = a[start];
            a[start]=a[end];
            a[end]=temp;
            start++;
            end--;
        }
    }

    private static void leftRotateArray( int d, final int[] a) {
     // create a temp array
        int[] temp = new int[a.length];
        int k=0;
        if(d> a.length)
        {
            d=d% a.length;
        }
        for(int i=d;i<a.length;i++){
            temp[k++]=a[i];
        }
        for(int i=0;i<d;i++){
            temp[k++]=a[i];
        }
        System.out.println(Arrays.toString(temp));
    }

    static List<Integer> leftRotate( int d,final List<Integer> list )
   {
       if(d>list.size())
       {
           d= d%list.size();
       }
       List<Integer> temp = new ArrayList<>(list.size());
       for(int i=d;i<list.size();i++)
       {
           temp.add(list.get(i));
       }
       System.out.println(temp);
       for(int i=0;i<d;i++)
       {
           temp.add(list.get(i));
       }

       return temp;
   }

}
