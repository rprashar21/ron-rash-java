package com.corejava.java8.streams.streamsApi;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsMaxAnMin {
    public static void main(String[] args) {

        final OptionalInt max = IntStream.range(1, 10)
                .max();

//        System.out.println(max.getAsInt());

        List<Integer> integerList = Arrays.asList(10,2,30,5);

        final Stream<Integer> sorted = integerList.stream().sorted(Comparator.comparing(Integer::intValue));

//        sorted.forEach(System.out::println);

        // maxBy and MIn by

        final Integer integer = integerList.stream()
                .max(Comparator.comparing(Integer::intValue)).get();

        System.out.println(integer);


        integerList.stream()
                .collect(maxBy(Comparator.comparing(Integer::intValue)));


        // or we can do this way as well
        final Optional<Integer> max1 = Arrays.asList(1,2,3).stream()
                .reduce((x, y) -> x > y ? x : y);

        System.out.println(max1.get());


        Comparator<Integer> comparator = (x,y)->x.compareTo(y);
        final Optional<Integer> max2 = integerList.stream()
                .max(comparator);

        System.out.println(max2.get());
    }
}
