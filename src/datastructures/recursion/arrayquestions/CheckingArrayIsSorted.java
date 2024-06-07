package datastructures.recursion.arrayquestions;

public class CheckingArrayIsSorted {

    public static void main(String[] args) {

        int[] array = new int[]{1,2,30,4,5};
        int index =0;
        System.out.println(isArraySorted(array,index));
    }

    private static boolean isArraySorted(final int[] array, final int index) {

        // base condition is when this thing has to stop
        if(index== array.length-1)
            return true; // why true bcz it has reached the end and it will only reach the end when the array is sorted

        return (array[index]<array[index+1] && isArraySorted(array,index+1));
    }
}
