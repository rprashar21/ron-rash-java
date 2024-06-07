package datastructures.arrays.Questions;

public class SecondLargestInArray {

    public static void main(String[] args) {
        /*
         *    This program loops through the array and keeps track of the largest and second largest elements it has seen so far.
         * If the current element is larger than the current largest element, we update the second largest element to be the previous largest element and the largest element to be the current element. If the current element is between the current largest and second largest elements, and it is not equal to the current largest element,
         * we update the second largest element to be the current element. Finally, we print out the second largest element
         *
         *
         * */

        //  int[] array = new int[]{10,9}; // 78 unsortded Array
        int[] array = new int[]{10, 9}; // 78 unsortded Array

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > largest) {
                secondLargest = largest;
                largest = array[i];
            } else if (array[i] > secondLargest && array[i] != largest) {
                secondLargest = array[i];
            }
        }
        System.out.println(secondLargest);
        findSecondLargest(array);

    }

    private static void findSecondLargest(final int[] array) {

        int second = array[0];
        int largest = array[0];

        for (int elm : array) {
            if (elm > largest) {
                second = largest;
                largest = elm;
            } else if (elm > second && elm != largest) {
                second = elm;
            }
        }
        System.out.println(String.format("secodn largest = %d largest = %d", second, largest));
    }
}
