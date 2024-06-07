package datastructures.hashmaps.hashmapQuestions;

import java.util.HashMap;

public class IsomorphicStrings {

    public static void main(String[] args) {
        final String s = "badc";
        final String t = "baba";

        // check if they are isomorphicc
//        All occurrences of a character must be replaced with another character while preserving the order of characters.
//                No two characters may map to the same character, but a character may map to itself.

//        Input: s = "egg", t = "add"
//        Output: true
//        Example 2:
//
//        Input: s = "foo", t = "bar"
//        Output: false
//        Example 3:
//
//        Input: s = "paper", t = "title"
//        Output: true

        System.out.println(isomorphic(s, t));

    }

    private static boolean isomorphic(final String s, final String t) {

        HashMap<Character, Character> mapst = new HashMap<>();
        HashMap<Character, Character> mapts = new HashMap<>();

        for(int i=0;i<s.length();i++){
           char c1 = s.charAt(i);
           char c2 = t.charAt(i);
           if(mapst.containsKey(c1))
           {
               char mapvalue = mapst.get(c1);
               if(mapvalue!=c2)
                   return false;
           }
           else {
               mapst.put(c1,c2);
           }

            if(mapts.containsKey(c2))
            {
                char mapvalue = mapts.get(c2);
                if(mapvalue!=c1)
                    return false;
            }
            else {
                mapts.put(c2,c1);
            }
       }
       return true;
    }
}
