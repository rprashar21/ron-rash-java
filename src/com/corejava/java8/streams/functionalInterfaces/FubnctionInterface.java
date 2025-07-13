package com.corejava.java8.streams.functionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FubnctionInterface {

    // functionInterface<T,R> takes an inout T retruns r
    //A Function<T,R> maps a T to an R.

    static Function<String, Integer> parseInt = s -> Integer.parseInt(s);

    public static void main(String[] args) {


        List<String> numbers = Arrays.asList("1","2");
        Set<Integer> allINtegers = numbers.stream().map(FubnctionInterface::convertToInt).collect(Collectors.toSet());
        System.out.println(allINtegers);

        // or
        Integer apply = parseInt.apply("1");
        System.out.println(apply);

    }

    private static int convertToInt(final String s) {

        return Integer.parseInt(s);
    }
}
