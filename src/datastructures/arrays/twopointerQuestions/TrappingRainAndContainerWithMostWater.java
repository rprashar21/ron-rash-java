package datastructures.arrays.twopointerQuestions;

public class TrappingRainAndContainerWithMostWater {

    // trapping Rain water
    // total amount of water that can be trapped between array of heights

    public static void main(String[] args) {

        // int[] a = {3,1,2,4,0,1,3,2};
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        //   int[] a = {3,1,0,1,2,1,0,3,0};

        int rainWater = trappingRainWater(a);
        System.out.println("Units of rain water trapped is "+rainWater);
    }

    private static int trappingRainWater(final int[] a) {
        // here 2 pinter solution
        // water can never be trapped on the ends on the end of buildings
        //The key idea to solve this problem is to understand that rainwater can only be trapped
        // if there exists a block of greater height, both on the left and the right side than the current block.
        // Then rainwater can be trapped on top of the block.

        //         int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        if (a.length == 1 || a.length == 0) {
            return 0;
        }
        int start = 1;
        int end = a.length-2;
        int maxLeft=a[0];
        int maxRight =a[a.length-1];
        int units =0;
         while(start<end)
         {
             if(a[start] <a[end])
             {
                 int diff = maxLeft - a[start];
                 if(diff > 0)
                   units= units+diff;

                 maxLeft= Math.max(maxLeft,a[start]);
                 start++;
             }else {
                 int diff = maxRight - a[end];
                 if(diff > 0)
                     units= units+diff;

                 maxRight= Math.max(maxRight,a[end]);
                 end--;
             }
         }

        return units;
    }
}
