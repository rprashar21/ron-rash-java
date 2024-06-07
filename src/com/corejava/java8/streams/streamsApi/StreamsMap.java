package com.corejava.java8.streams.streamsApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsMap {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("A",1); // entryset
        map.put("b",2);
        map.put("C",3);
        map.put("d",4);

        final List<Map.Entry<String, Integer>> list = map.entrySet().stream().collect(Collectors.toList());

        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
    }
}
