package com.corejava.corejava.exeptionHandling.trywithResources;

public class SampleQuiz2 {

    public static void main(String[] args) {

        String s = v1();
        System.out.println(s);

        v2();
    }

    private static String v1() {

        String hello = "hello";
        try {
            if ("hello0".equals(hello)) {
                System.out.println("hello");
                return hello;
            }
            throw new RuntimeException("some exception");
            // here we are not catching the exception program will terminate and not excute the v2 methid although it will execute finallly block
        }catch (Exception e)
        {
            // now we are catching this exception and not terminating the program v2 method will be excuted
            System.out.println("catch block ");
            throw new RuntimeException("Another exception ");
        }
        finally {
            System.out.println("finally block will always be excuted");
        }
    }

    private static void v2() {

        System.out.println("v2 method ");
    }
}
