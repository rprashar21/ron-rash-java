package com.corejava.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class MaxInteger {

    public Optional<Integer> findMax(Collection<Integer> numbers) {

        final Optional<Collection<Integer>> optionalCollection = Optional.ofNullable(numbers);


        final Optional<Integer> maxNumber = numbers.stream()
                .filter(i -> i != null)
                .reduce((a, b) -> a > b ? a : b);

        return maxNumber.isPresent() ? maxNumber : Optional.empty();

    }

    public static void main(String[] args) {

        List<Integer> integers = null;
        Optional<Integer> optionalInteger = FRomList(integers);
        optionalInteger.ifPresent(value -> System.out.println("printing the answer" + value.intValue()));
    }

    private static Optional<Integer> FRomList(final List<Integer> integers) {

    //    Objects.requireNonNull(integers, "list cannot be null");

        if (Objects.nonNull(integers)) {
            return integers.stream()
                    .filter(Objects::nonNull)
                    .reduce((a, b) -> a > b ? a : b);
        }

        return Optional.empty();
    }
}

