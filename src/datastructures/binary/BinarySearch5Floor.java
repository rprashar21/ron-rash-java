package datastructures.binary;

public class BinarySearch5Floor {


    public static void main(String[] args) {
        final int[] array = new int[]{10,20,30,40,50,60,70,80,90,100}; //10,20,30,40,50,60,70,80,90,100
        final int target = 50;
        final int index = floorBs(array, target);
        System.out.println("floor is "+index);

        // floor is basically the number which is greater than all numbers but less than target

        // what is the target elemnt is smaller than or greater than start or end

    }

    static int floorBs(int[] array,int target)
    {
        //
        int start=0;
        int end =array.length-1;

        if(target<array[start] || target>array[end])
            return -1;

        while (start<=end)
        {
            int mid = start +(end-start)/2;

            if(target>array[mid])
                start= mid+1;
            else
                end=mid-1;
        }
        return end;
    }
}
