package datastructures.arrays.twodimarrays;

public class SearchInNonSortedArray {

    public static void main(String[] args) {

        // with arrays
        int[][] array = new int[][]{{12,4,5},{90,8},{100}};
        int target = 8;
       boolean isElementPresent= searchInArray(array,target);
        System.out.println(isElementPresent);

    }

    private static boolean searchInArray(final int[][] a,final int target)
    {
        // 2d array
//        for(int[] array :a )
//        {
//            // array search in this
//            for(int value :array)
//            {
//                if(value==target)
//                {
//                    return true;
//                }
//            }
//        }
//        return false;

        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<a[i].length;j++)
            {
                if(a[i][j]==target)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
