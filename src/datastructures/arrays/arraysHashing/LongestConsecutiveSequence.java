package datastructures.arrays.arraysHashing;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {

        //
       // int[] a = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}; // [1,2,3,4] [100] [200]
     //   int[] a = {10, 9, 8, 5, 8, 4, 3, 2, 1};
        int[] a ={19,11,20,10,5,3,2,1};
        longestConsecutiveSequenceBetterSolution(a);
        practice(a);

        // steps put all the elemnts in a set as to remove the duplicates and then check for starting o the sequence
        // loop until the end of that sequence using set

    }

    private static void longestConsecutiveSequenceBetterSolution(final int[] a) {

        HashSet<Integer> set = new HashSet<>();
        for (Integer num : a) {
            set.add(num); // O(1) constant time set uses hashing and it always takes []
        }
        // we have a set []
        int finalLength = 0;
        for (Integer currentNumber : a) {

            // check if the currentNUmber is starting of the sequence
            if (!set.contains(currentNumber - 1)) // if the set does nt contain the number -1 then this number is the starting sequence
            {
                int length = 1;
                while (set.contains(currentNumber + 1)) {
                    currentNumber = currentNumber + 1;
                    length++;
                }
                finalLength = Math.max(finalLength, length);
            }
        }
        System.out.println(finalLength);
    }

    private static void practice(final int[] a) {
        //
        Set<Integer> set = new HashSet<>();
        int finalCount = 0;
        for (int i : a) {
            set.add(i); // remove any duplicates
        }
        for (int i : a) {
            if (!set.contains(i - 1)) {
                int startingElem = i;
                int count = 1;
                while (set.contains(startingElem + 1)) {

                    count++;
                    startingElem = startingElem + 1;
                }
                if(count>=finalCount)
                    finalCount=count;
            }
        }
        System.out.println(finalCount);
    }

    //        Explanation:
//
//        Constructing the Set: The first loop iterates over each element in the array and inserts it into a HashSet. Since HashSet operations like add and contains have an average time complexity of O(1), this loop has a time complexity of O(n), where n is the length of the array.
//
//                Finding the Longest Consecutive Sequence: The second loop iterates over each element in the array. For each element, it checks if the element minus 1 is present in the set using set.contains(a[i]-1). This check has a time complexity of O(1) on average.
//
//        If the element minus 1 is not present, it means the current element is the start of a new sequence. In that case, it enters a while loop that increments a counter j and checks if the element plus j is present in the set. The while loop continues until the consecutive sequence ends. The while loop takes at most O(n) iterations because it checks each element exactly once.
//
//        Inside the while loop, it removes elements from the set using set.remove(a[i]+1). Removing an element from the HashSet has an average time complexity of O(1).
//
//                Overall, the second loop has a time complexity of O(n) since each element is checked and removed from the set at most once.
//
//        Therefore, the overall time complexity of the code is O(n) due to the linear iteration over the array.
}
