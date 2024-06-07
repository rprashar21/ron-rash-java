package com.corejava.corejava.exeptionHandling.trywithResources;

public class SampleException {

    // The java.lang.Throwable class is the root class of Java Exception hierarchy inherited by two subclasses: Exception and Error.
    // 3 types of exception checked uncheked and error
    // checked at compile time - ioexception,sqlexception,fileIoException
    // throw keywoard is used to throw an exception
    //"throws" keyword is used to declare exceptions.
    // It specifies that there may occur an exception in the method. It doesn't throw an exception. It is always used with method signature.
    // At a time only one exception occurs and at a time only one catch block is executed.
    //All catch blocks must be ordered from most specific to most general, i.e. catch for ArithmeticException must come before catch for Exception.

    public static void main(String[] args) {
        try
        {
            int data=50/0; //may throw exception

        }
        // try to handle the ArithmeticException using ArrayIndexOutOfBoundsException
        catch(ArithmeticException e) // this is a proper exception -- program does not terminate
        {
            System.out.println(e);
        }
        System.out.println("rest of the code");
    }
}
