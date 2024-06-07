package com.corejava.java8.streams.streamsApi;

import java.util.stream.IntStream;

public class ParallelStreams {
    public static void main(String[] args) {

        /*
        *Splits the source of data into multiple parts
        * process them parallelly and combne the result an gives you the data
        * Parallel stream works on fork/Join Framework -- no of threds = no of processor availbale in the machine
        * */
        long start = System.currentTimeMillis();
        final int sum = IntStream.rangeClosed(1, 30000).parallel().sum();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(sum);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
