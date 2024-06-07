package com.corejava.Generics.assignment2;

public class PrnterApp {
    public static void main(String[] args) {
        Printer<Integer> integerPrinter = new Printer<>("Integer Printer ");
        integerPrinter.print(2);

        Printer<String> stringPrinter = new Printer<>("String printer");
        stringPrinter.print(" string value");
    }
}
