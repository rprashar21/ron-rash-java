package datastructures.arrays.twopointerQuestions;

public class TrappingRainAndContainerWithMostWater {

    // trapping Rain water
    // total amount of water that can be trapped between array of heights

    public static void main(String[] args) {

        int[] a = {3, 1, 2, 4, 0, 1, 3, 2};
         int[] a1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
         int[] a2 = {3,1,0,1,2,1,0,3,0};

        int rainWater = trappingRainWater(a1);
        trappingRainWater2(a);
        trappingRainWater2(a1);
        trappingRainWater2(a2);
        System.out.println("Units of rain water trapped is " + rainWater);
    }

    private static int trappingRainWater(final int[] a) {
        // here 2 pinter solution
        // water can never be trapped on the ends on the end of buildings so they should be mazleft and maxright ie length
        //The key idea to solve this problem is to understand that rainwater can only be trapped iwthin the buildinsgs
        // if there exists a block of greater height, both on the left and the right side than the current block.
        // Then rainwater can be trapped on top of the block.

        //         int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        if (a.length == 1 || a.length == 0) {
            return 0;
        }
        int start = 1;
        int end = a.length - 2;
        int maxLeft = a[0];
        int maxRight = a[a.length - 1];
        int units = 0;
        while (start < end) {
            if (a[start] < a[end]) {
                int diff = maxLeft - a[start];
                if (diff > 0)
                    units = units + diff;

                maxLeft = Math.max(maxLeft, a[start]);
                start++;
            } else {
                int diff = maxRight - a[end];
                if (diff > 0)
                    units = units + diff;

                maxRight = Math.max(maxRight, a[end]);
                end--;
            }
        }

        return units;
    }

    private static int trappingRainWater2(int[] a) {

        // rain water is a 2 pointer problem
        if (a == null || a.length == 0 || a.length == 1) {
            return 0;
        }

        int s = 1;
        int end = a.length - 2;

        int maxleft = a[0];
        int maxRight = a[a.length - 1];

        int waterStored = 0; // water stored in units

        System.out.println("value of start is " + s + " and end is " + end + " and max left is " + maxleft + " and max right is " + maxRight);

        while (s < end) {
            if (a[s] < a[end]) {
                int diff = maxleft - a[s];
                if (diff > 0) {
                    System.out.println("water can be stored " + diff);
                    waterStored = waterStored + diff;
                }
                maxleft = Math.max(maxleft, a[s]);
                s++;
            } else {
                int diff = maxRight - a[end];
                if (diff > 0) {
                    System.out.println("water can be stored " + diff);
                    waterStored = waterStored + diff;
                }
                maxRight = Math.max(maxRight, a[end]);
                end--;
            }
        }
        System.out.println("for this array water stored is " + waterStored);
        return waterStored;
    }
}
