package com.corejava.java8.streams;


import java.util.List;
import java.util.Optional;

public class MaxNumberValidator {

    public Optional<Integer> calculate(final List<Integer> list) {



        if (null==list|| list.isEmpty())
            throw new IllegalStateException("List should not be empty ");

        return list.stream()
                .filter(s->s!=null)
                .max(Integer::compareTo);
      
    }
}
