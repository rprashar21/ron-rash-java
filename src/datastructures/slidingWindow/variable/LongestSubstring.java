package datastructures.slidingWindow.variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstring {

//    Example 1:
//
//    Input: s = "abcabcbb"
//    Output: 3
//    Explanation: The answer is "abc", with the length of 3.
//    Example 2:
//
//    Input: s = "bbbbb"
//    Output: 1
//    Explanation: The answer is "b", with the length of 1.
//    Example 3:
//
//    Input: s = "pwwkew"
//    Output: 3
//    Explanation: The answer is "wke", with the length of 3.
//    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring
    public static void main(String[] args) {

        lengthOfLongestSubstring("abcabcbb");
        lengthOfLongestSubstring("bbbbb");
        lengthOfLongestSubstring("pwwkew");
        lengthOfLongestSubstring("abcabcdafg");
        lengthOfLongestSubstring("dvdf");

        System.out.println("Using a hashmap Complexity is 0(n) and space is 0(1)");
        // using a hashmap
        lengthOfLongestSubstring2("dvdf");
        lengthOfLongestSubstring2("abcabcbb");
        lengthOfLongestSubstring2("abcabcdafg");
        lengthOfLongestSubstring2("bbbbb");
    }

    private static int lengthOfLongestSubstring(String s) {
        List<String> list = new ArrayList<>();
        int maxlength = 1;
        if(s.isEmpty() || s.length() == 1) {
            return s.length();
        }
        list.add(String.valueOf(s.charAt(0)));
        int i=0;
        for (int j = 1; j < s.length(); j++) {
            String current = String.valueOf(s.charAt(j));
            if(list.contains(current)) {
                maxlength = Math.max(maxlength, list.size());

                while(list.contains(current)) {
                    list.remove(0);
                    i++;
                }
            }
            list.add(String.valueOf(s.charAt(j)));

        }
        maxlength = Math.max(maxlength, list.size());
        System.out.println("for string "+s+" maxLength is " + maxlength);
        return maxlength;
    }

    // using a hashmap
    private static int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        while (j < s.length()) {
            char ch = s.charAt(j);
            if (map.containsKey(ch)) {
                i = Math.max(map.get(ch), i);
            }
            maxLength = Math.max(maxLength, j - i + 1);
            map.put(ch, j + 1);
            j++;
        }
        System.out.println("for string "+s+" maxLength is " + maxLength);
        return maxLength;
    }
}
