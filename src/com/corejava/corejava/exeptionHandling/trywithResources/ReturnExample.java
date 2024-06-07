package com.corejava.corejava.exeptionHandling.trywithResources;

public class ReturnExample {
    public static int divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            // Handle division by zero exception
         //   throw new RuntimeException("exception occurred"); // this is an
            // here we can print the exceprton
            System.out.println(e.getMessage());
            return 0; // Return a default value // thisis an unreachable statement
        } finally {
            System.out.println("Finally block executed");
        }
    }

    public static void main(String[] args) {
        int result = divide(10, 0);
        System.out.println("Result: " + result);
    }
}
