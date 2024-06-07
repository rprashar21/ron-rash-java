package datastructures.hashmaps.hashmapQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintAllAnagrams {
    public static void main(String[] args) {


        String a[] = {"cat", "dog", "tac", "god", "act"};
        /// all annagram should come together
        // cat,tac,act -- dog god
        // steps complexity 0(n)  -- sort act  -- List<cat, , dgo
        // sort the string and put in map
        // map<string,list<string>>


        //  findAnagrams(a);
        findAnagrams1(a);
    }

    private static void findAnagrams(final String[] a) {

        // hashMap
        Map<String, List<String>> map = new HashMap<>();

        // loop thru the lsit ===> 0(n)
        // cover them to a char and sort them
        // put it in hash map 0(1)

        for (int i = 0; i < a.length; i++) {
            String word = a[i];
            // sort this
            final char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String newWord = new String(chars);

            if (!map.containsKey(newWord)) {
                List list = new ArrayList();
                list.add(word);
                map.put(newWord, list);
            } else {
                map.get(newWord).add(word);
            }

        }
        System.out.println(map);
        map.forEach((k, v) -> System.out.println(map.get(k)));
    }

    private static void findAnagrams1(final String[] a) {
        Map<String, List<String>> map = new HashMap<>();
        // loop
        for (String actualWord : a) {
            String temp = actualWord;
            // sort the string
            final char[] chars = temp.toCharArray();
            Arrays.sort(chars);
            String newWord = new String(chars);

            if (map.containsKey(newWord)) {
                map.get(newWord).add(actualWord);
            } else {
                List<String> list = new ArrayList<>();
                list.add(actualWord);
                map.put(newWord, list);
            }
        }

        // extract the list from the map and display the itmes in
       List<String> stringList = new ArrayList<>();
        final Collection<List<String>> values = map.values();

        final List<String> collect = map.values()
                .stream()
                .flatMap(coll->coll.stream())
                .collect(Collectors.toList());


        final Collection<List<String>> values1 = map.values();

        System.out.println( Arrays.toString(collect.toArray()));

    }

}
