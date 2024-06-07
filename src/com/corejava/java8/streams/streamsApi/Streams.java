package com.corejava.java8.streams.streamsApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Streams {
    public static void main(String[] args) {

        final List<String> stringList = new ArrayList<>();
        stringList.add("whee");



        final List<String> list = Arrays.asList("winner");
            stringList.addAll(list);
                printListFList(stringList);
    }

    private static void printListFList(final List<String> stringList) {
        System.out.println(stringList);
    }
}
