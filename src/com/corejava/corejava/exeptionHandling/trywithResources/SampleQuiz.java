package com.corejava.corejava.exeptionHandling.trywithResources;

public class SampleQuiz {

    public static void main(String[] args) {

        try{
            m1(); // since this method is inside this try block whatever exception we throw here it will be caught in the exception block
        }
        catch (RuntimeException ex)
        {
            System.out.println("B");
            throw new RuntimeException(""); // no catch block to catch this excetioon
        }
        catch (Exception e)
        {
            System.out.println("C");
        }
        finally {
            System.out.println("D");
        }
        System.out.println("E");
    }

    private static void m1() {
        throw new RuntimeException();

        // ans is BDE
    }
}
