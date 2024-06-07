package datastructures.binary.Questions;

public class NextGreatestletter {

    // smallest letter greater than target
    // array is sorted in non dreasing order
    // retrn the  smallest character int the array that is larger than the target
    // cieling question
    /*
     * examples
     * {a,b,c} target b   ans c
     * {a,b}   target  z  ans - a --> wrap around if we dont find any element then return 1st one
     * {c,f,j} target c   ans f
     * {c,f,j} target a   ans c
     * */

    public static void main(String[] args) {
        char[] letters = new char[]{'c', 'f', 'j'}; // answer is c
        char target = 'c';
        final char nextGreatestLetter = nextGreatestLetter(letters, target);
        System.out.println(nextGreatestLetter);

        /*
         * Solution approach
         * Same approach as cieling instead that we need to remove the equals condition because it wants the next greatest
         * we have to return the start % length of an aray
         * */
    }

    private static char nextGreatestLetter(final char[] a, final char target) {

        int start = 0;
        int end = a.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target >=a[mid])
                start = mid + 1;
            else
                end = mid - 1;
        }
        return a[start%a.length];
    }
}
