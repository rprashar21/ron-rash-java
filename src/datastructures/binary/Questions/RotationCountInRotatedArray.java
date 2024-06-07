package datastructures.binary.Questions;

public class RotationCountInRotatedArray {
    public static void main(String[] args) {
        int[] array ={15,18,2,3,6,12};

        // find the no of rotations in a sorted array we need to find the position of the pivot or max element
        // the position of the max elemnt +1 is

        // or find the position of the smallest element


        System.out.println(countRotationsWithMinElement(array));
    }

    public static int countRotations(int[] array)
    {
        int index=0;
        int maxElement=array[0];
        for(int i=0;i<array.length;i++)
        {
            if(array[i]>maxElement)
            {
                maxElement=array[i];
                index=i;
            }
        }
        if(index== array.length-1)
        {
            return 0;
        }
        return index+1;
    }
    public static int countRotationsWithMinElement(int[] array)
    {
        int index=0;
        int minElement=array[0];
        for(int i=0;i<array.length;i++)
        {
            if(array[i]<minElement)
            {
                minElement=array[i];
                index=i;
            }
        }
        return index;
    }
}
