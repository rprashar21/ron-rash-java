package com.corejava.corejava.comparableandcomparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CustomSorting {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<String>();
        strings.add("Sunday");
        strings.add("Monday");
        strings.add("Tuesday");
        strings.add("Wednesday");

        HashMap<String,Integer> map = new HashMap<>();
        map.put("Sunday",1);
        map.put("Monday",2);
        map.put("Tuesday",3);
        map.put("Wednesday",4);
        map.put("Thursday",5);
        map.put("Friday",6);
        map.put("Saturday",7);

        // sorth this map according to its values and then extact a list


        TreeMap<String, Integer> sortedMap = new TreeMap<>(map); // this will sort it based on the keys

        System.out.println("sorted based on the keys "+sortedMap);

        // we can pass a comparator and explicity say sor he original map based on values

         // lets pass a comparator
        TreeMap<String, Integer> valueSortMap = new TreeMap<>(Comparator.comparing(map::get));
         valueSortMap.putAll(map);
        System.out.println("sorted based on the values "+valueSortMap);


        // or compare like this
        TreeMap<String, Integer> valueSortMap1 = new TreeMap<>((v1,v2)->map.get(v1)-map.get(v2));
        TreeMap<String, Integer> valueSortMap2 = new TreeMap<>((v1, v2) -> map.get(v2).compareTo(map.get(v1)));

    }
}

class ValueComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
       return Integer.compare(o1, o2);
    }
}
