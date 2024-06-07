package datastructures.hackerrank.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FindUniqueElement {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 3, 2, 1);

        // using java 7
        int lonelyInteger = lonelyIntegerUsingMapGetOrDefault(integers);

        // using java 8
        System.out.println(lonelyInteger);
    }

    private static int lonelyInteger(final List<Integer> list) {

      // brute force
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (Integer integer : list) {
            if (maps.containsKey(integer)) {
                maps.put(integer, maps.get(integer) + 1);
            } else {
                maps.put(integer, 1);
            }
        }
        System.out.println(maps);
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if (entry.getValue() == 1) {
              return entry.getKey();
            }
        }
        return -1;
    }

    private static int lonelyIntegerUsingMapGetOrDefault(final List<Integer> list) {

        // brute force
        HashMap<Integer, Integer> maps = new HashMap<>();
        for (Integer integer : list) {
          maps.put(integer,maps.getOrDefault(integer,0)+1);
        }
        System.out.println(maps);
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
