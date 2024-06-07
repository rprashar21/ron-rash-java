package datastructures.sorting;

public class MergeArray {



    public static void main(String[] args) {
        int [] array1= new int[]{4,5,6,10};
        int [] array2= new int[]{1,6,8,20};

        mergeSortArray(array1,array2);
    }

    private static void mergeSortArray(final int[] array1, final int[] array2) {

        int[] finalArray = new int[array1.length+ array2.length];

        // loop thru the array[4,5,6,10] array[1,6,8,20] --[1,4,5,6]
        int i=0,j=0,k=0;
        while(i < array1.length )
        {
            if(array1[i]<= array2[j])
            {
                finalArray[k++]=array1[i];
            }
            else {
                finalArray[k++]=array2[j];
            }

            if(j== array2.length)
            {}
        }
    }
}
