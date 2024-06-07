package datastructures.leetcode;

import java.util.HashMap;

public class IsValidAnagram {
    public static void main(String[] args) {

        String s = "acbb";
        String t = "bbac";

        System.out.println(isvalidAngram(s, t));
        System.out.println(isNewvalidAngram(s, t));
        System.out.println(isValidAna(s, t));
    }

    private static boolean isvalidAngram(final String s, final String t) {

        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++; //[0,1]
            chars[t.charAt(i) - 'a']--;
        }
        for (int count : chars) {
            if (count != 0)
                return false;
        }
        return true;
    }

    private static boolean isNewvalidAngram(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] array = new int[26];
        int count = 1;
        for (int i = 0; i < s.length(); i++) {

            final int x = s.charAt(i) - 'a';
            array[x]++;

            final int y = t.charAt(i) - 'a';
            array[y]--;
        }
        for (int counter : array) {
            if (counter != 0)
                return false;
        }
        return true;
    }

    private static boolean isValidAna(final String s, final String t) {

        if(s.length() != t.length())
            return false;

        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        System.out.println(map);
        for (Character c : t.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            if (count == 0)
                return false;
            map.put(c, count-1);
        }
        return true;
    }
}
