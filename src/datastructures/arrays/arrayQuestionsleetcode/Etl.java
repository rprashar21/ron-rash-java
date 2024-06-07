package datastructures.arrays.arrayQuestionsleetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etl {
    public static Map<String, Integer> transform(Map<Integer, List<String>> scoreMap) {
        Map<String, Integer> transformedMap = new HashMap<>();
        scoreMap.forEach((score, letters) -> {
            letters.forEach(letter -> {
                transformedMap.put(letter.toLowerCase(), score);
            });
        });
        return transformedMap;
    }

    public static void main(String[] args) {
        Map<Integer, List<String>> scoreMap = new HashMap<>();

        scoreMap.put(8, List.of("J", "X"));
        scoreMap.put(10, List.of("Q", "Z"));

        Map<String, Integer> result = transform(scoreMap);
        result.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}


