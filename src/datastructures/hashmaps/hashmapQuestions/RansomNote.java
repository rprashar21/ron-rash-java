package datastructures.hashmaps.hashmapQuestions;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

//    Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
//
//    Each letter in magazine can only be used once in ransomNote.
//
//    Example 1:
//
//    Input: ransomNote = "a", magazine = "b"
//    Output: false
//    Example 2:
//
//    Input: ransomNote = "aa", magazine = "ab"
//    Output: false
//    Example 3:
//
//    Input: ransomNote = "aa", magazine = "aab"
//    Output: true

    public static void main(String[] args) {
        final String ransomeNote = "bg";
        final String magazine = "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj";
        //  System.out.println(canConstruct(ransomeNote, magazine));
        System.out.println(canConstructNew(ransomeNote, magazine));
    }

    public static boolean canConstructNew(String ransomNote, String magazine) {

        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : ransomNote.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Character ch : magazine.toCharArray()) {
            if(map.containsKey(ch)) // there might be characters in the second string we have to skip
            {
                int count = map.getOrDefault(ch, 0);
                if (!map.isEmpty() && count == 0)
                {
                    return false;
                }
                else if(!map.isEmpty()){
                    map.put(ch, count - 1);
                    if (map.get(ch) == 0)
                        map.remove(ch);
                }
            }
        }

        return map.isEmpty() ? true : false;
    }

}


