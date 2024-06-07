package com.corejava.java8.streams.functionalInterfaces;

import java.util.function.Consumer;

public class ConsumerInterface {


    // functional Interface 4
    // 1. Consumer Interface
    // 2. Function Intefrace
    // 3. predicate Interface

    //

  static   Consumer<String> stringConsumer =(s)->{
        System.out.println(s.toLowerCase());
    };

    public static void main(String[] args) {

        stringConsumer.accept("ROHIT");
        integerConsumer.accept(2);
    }
    static  Consumer<Integer> integerConsumer =(s)-> System.out.println(s.intValue()*s.intValue());

}
