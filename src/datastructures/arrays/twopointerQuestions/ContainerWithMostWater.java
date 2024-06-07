package datastructures.arrays.twopointerQuestions;

public class ContainerWithMostWater {

    //The "container with most water" problem involves finding the largest
    // possible area that can be formed by two vertical lines on a graph,
    // given a series of non-negative integers representing the height of the lines.
    public static void main(String[] args) {
        //int[] a = {1,1};//{12,11}
        int[] a = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = maxArea(a);
        System.out.println(maxArea);

        System.out.println(mostWater(a));


        bruteForceSolution(a);

    }

    private static int mostWater(final int[] a) {
        int start = 0;
        int end = a.length - 1;
        int height;
        int len;
        int maxArea = Integer.MIN_VALUE;
        while (start < end) {
            if (a[start] < a[end]) {
                len = end - start;
                height = Math.min(a[start], a[end]);
                maxArea = Math.max((len * height), maxArea);
                start++;
            } else {
                len = end - start;
                height = Math.min(a[start], a[end]);
                maxArea = Math.max((len * height), maxArea);
                end--;
            }
        }
        return maxArea;
    }


    private static void bruteForceSolution(final int[] a) {

        int lenght = 0;
        int breadth = 0;
        int maxArea = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                lenght = a[i] < a[j] ? a[i] : a[j];

                breadth = Math.abs(j - i);
                maxArea = Math.max((lenght * breadth), maxArea);
            }
        }

        System.out.println(maxArea);
    }

    public static int maxArea(int[] a) {
        int left = 0;
        int right = a.length - 1;
        int length = 0;
        int breadth = 0;
        int maxarea = 0;
        while (left < right) {
            if (a[left] < a[right]) {
                length = a[left];
                breadth = right - left;
                left++;
            } else {
                length = a[right];
                breadth = right - left;
                right--;
            }
            maxarea = Math.max(length * breadth, maxarea);
        }
        return maxarea;
    }
}
