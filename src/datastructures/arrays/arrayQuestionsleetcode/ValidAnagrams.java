package datastructures.arrays.arrayQuestionsleetcode;

import java.util.HashMap;

public class ValidAnagrams {
    public static void main(String[] args) {

        //
        String s = "bat"; // a =2 c=2
        String t = "tab";
        boolean value = checkValidAnagrams(s, t);
        System.out.println(value);

        boolean isValidAnagarm = checkValidAnagramsViaArray(s,t);
        System.out.println(isValidAnagarm);
    }

    private static boolean checkValidAnagramsViaArray(final String s, final String t) {
        // this method will take extra space
        int [] array = new int[26];//[2,0,2,0]

        if(s.length()!=t.length())
        return false;

        for(int i=0;i<s.length();i++)
        {
            array[s.charAt(i)-'a']++;
            array[t.charAt(i)-'a']--;
        }
        // loop thru the array if at any position i find there is anything other than 0 return false

        for(int i=0;i< array.length;i++)
        {
            return array[i] == 0;
        }
        return true;
    }

    private static boolean checkValidAnagrams(final String s, final String t) {

        if (s.length() != t.length())
            return false;

        boolean result = true;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                Integer value = map.get(t.charAt(i));

                map.put(t.charAt(i), value - 1);
                if (map.get(t.charAt(i)) == 0) {
                    map.remove(t.charAt(i));
                }

            } else {
                result = false;
            }
        }
        return result;
    }


}
