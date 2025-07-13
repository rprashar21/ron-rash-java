package com.corejava.java8.streams.functionalInterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ConsumerInterface {


    // functional Interface 4
    // 1. Consumer Interface<T> takes an input retruns nothing accept
    // 2. Function Intefrace <T,R> takes an Input T retruns R --> apply
    // 3. predicate Interface <T> takes an input T return true or false
    // 4. Supplier A Supplier<T> produces a T with no input.

    //

  static   Consumer<String> stringConsumer =(s)->{
        System.out.println(s.toLowerCase());
    };

    public static void main(String[] args) {

        stringConsumer.accept("ROHIT");
        integerConsumer.accept(2);
        Object order = new Object();


        // Consumer: side-effect
        Consumer<String> printer = s -> System.out.println(s.toLowerCase());

// Function: transform
        Function<String, Integer> toLen = String::length;

// Predicate: test
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("testing even "+isEven.test(4));
        System.out.println("testing even "+isEven.test(4));

// Supplier: produce
        Supplier<Double> randomSupplier = Math::random;

// Use in a mini pipeline:
        List<String> names = List.of("Alice","Bob","Charlie");
        names.stream()
                .filter(name -> isEven.test(toLen.apply(name)))
                .peek(printer)               // prints lower-cased even-length names
                .map(toLen::apply)           // then maps to length
                .forEach(len -> System.out.println("Len="+len));



        double randomValue = randomSupplier.get();


    }
    static  Consumer<Integer> integerConsumer =(s)-> System.out.println(s.intValue()*s.intValue());







}
