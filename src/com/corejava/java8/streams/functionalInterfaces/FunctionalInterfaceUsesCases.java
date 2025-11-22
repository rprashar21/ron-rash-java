package com.corejava.java8.streams.functionalInterfaces;

import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalInterfaceUsesCases {


    public static void main(String[] args) {
        Supplier<Double> supply = () -> {

            System.out.println("Hello World");
            return 0.0;
        };


        FunctionalInterfaceUsesCases obj = new FunctionalInterfaceUsesCases();

        obj.execcute(supply);
        obj.execcute(() -> {
            int sum = 0;
            for (int i = 0; i < 2; i++) {
                sum += i;
                System.out.println("second" + sum);
            }
            return sum;
        });

        obj.func(5,x->x*x);
    }


    public <T> T execcute(Supplier<T> supplier) {
        return supplier.get();
    }

    public <T> T func(T input, Function<T, T> function) {

        return function.apply(input);

    }
}


