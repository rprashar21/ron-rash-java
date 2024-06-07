package datastructures.stacks.questions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;

public class CarFleet {

    public static void main(String[] args) {
//        Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
//Output: 3
//Explanation:
//The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
//The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
//The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
//Note that no other cars meet these fleets before the destination, so the answer is 3.
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};
        carFleet(position, speed, target);
    }

    private static int carFleet(final int[] position, final int[] speed, final int target) {
        // first create pais and then sort the pairs based  based on key and put them in a array

        if (position.length == 1)
            return 1;

        PairFleet[] pair = new PairFleet[position.length];
        for (int i = 0; i < position.length; i++) {
            pair[i] = new PairFleet(position[i], speed[i]);
        }
        // sort this based onkeys i Have used a comparator class Comparatr is used for default sorting of elemets
        Collections.sort(Arrays.asList(pair), new PairKeyComparator());


        System.out.println(Arrays.toString(pair));

        Deque<Double> stack = new ArrayDeque<>();
        for (int i = pair.length - 1; i >= 0; i--) {
            double timeUnits =  (double)(target - pair[i].key) / (pair[i].value);
            stack.push(timeUnits);

            if (stack.size() >= 2) {
                // check for conditions
                double peek1 = stack.pop();
                double peek2 = stack.peek();
                if (peek1 <= peek2) {
                    continue;
                } else {
                    stack.push(peek1);
                }
            }

        }
        System.out.println(stack.size());
        return stack.size();
    }
}

class PairFleet {
    public int key;
    public int value;

    public PairFleet(final int key, final int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "PairFleet{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

class PairKeyComparator implements Comparator<PairFleet> {

    public int compare(PairFleet obj1, PairFleet obj2) {
        return obj1.key > obj2.key ? 1 : -1;
    }
}