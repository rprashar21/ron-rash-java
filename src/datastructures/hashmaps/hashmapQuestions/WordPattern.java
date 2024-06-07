package datastructures.hashmaps.hashmapQuestions;

import java.util.HashMap;

public class WordPattern {

//    Given a pattern and a string s, find if s follows the same pattern.
//    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
//    Example 1:
//
//    Input: pattern = "abba", s = "dog cat cat dog"
//    Output: true
//    Example 2:
//
//    Input: pattern = "abba", s = "dog cat cat fish"
//    Output: false
//    Example 3:
//
//    Input: pattern = "aaaa", s = "dog cat cat dog"
//    Output: false

    public static void main(String[] args) {
        final String pattern = "aaa";
        final String s = "aa aa aa aa";

        // check if they are isomorphicc

        System.out.println(wordPattern(pattern, s));

    }

    private static boolean wordPattern(final String pattern, final String s) {
        final HashMap<Character, String> map = new HashMap<>();
        final HashMap<String, Character> smap = new HashMap<>();
        String[] array = s.split(" ");
        if(pattern.length()!= array.length)
            return false;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String temp= array[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(temp))
                    return false;
            } else {
                map.put(c, temp);
            }
            if(smap.containsKey(temp))
            {
                if (smap.get(temp)!=c)
                    return false;
            }else {
                smap.put(temp,c);
            }
        }
        return true;
    }
}
