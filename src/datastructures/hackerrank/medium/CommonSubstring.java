package datastructures.hackerrank.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommonSubstring {

    public static void main(String[] args) {
        // determine if 2 strings have anything in cmmon

        String s1 = "barbell";
        String s2 = "trapper";

        // brute force
        String common = common(s1, s2);
        System.out.println(common);

        // optimized we can take a set --  s1 barel s2 trape  s1.retainAll
        System.out.println(optimizedSolution(s1, s2));
        ;

    }

    private static String optimizedSolution(final String s1, final String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (Character s : s1.toCharArray()) {
            set1.add(s);
        }
        for (Character s : s2.toCharArray()) {
            set2.add(s);
        }
        // retain the leelments wheich are common  in set 1
        set1.retainAll(set2);
        if (!set1.isEmpty())
            return "YES";
        return "NO";
    }

    private static String common(final String s1, final String s2) {

        // s1
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            if (map.containsKey(s2.charAt(i)))
                return "YES";

        }
        return "NO";
    }
}
