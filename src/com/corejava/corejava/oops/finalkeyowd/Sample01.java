package com.corejava.corejava.oops.finalkeyowd;

public class Sample01 {

    // final can be used on a class --> not extendable ,, method not override ,, variable constant
    // Yes, final method is inherited but you cannot override it.
    // A final variable that is not initialized at the time of declaration , should be initialized at the time of a constructor
   // A static final variable that is not initialized at the time of declaration is known as static blank final variable.
    // It can be initialized only in static block.

  //  If you declare any parameter as final, you cannot change the value of it.

    /*
    * finalize: A method defined in the Object class. It is called by the garbage collector on an object
    * when garbage collection determines that there are no more references to the object.
    * It's generally not recommended to rely on finalize for any critical operation because you can't predict when, or if,
    * the garbage collector will collect a particular object.
    *
    *
    *
    * */

    final String name;

    public Sample01(final String name) {
        this.name = name;
    }
    
    static final String allocation = "yes";

    public static void main(String[] args) {
        
    }

    public void calculate(final int value)
    {
       // value = value*2; compile time error
    }
}
class SampleObject {
    // This method is called by the garbage collector on the object
    protected void finalize() throws Throwable {
        try {
            System.out.println("Cleanup operations or resource release logic here.");
        } finally {
//            : It's a good practice to call the finalize method of the superclass at the end of your finalize method.
//            This ensures that the finalize method of the superclass can perform its cleanup activities as well.
            super.finalize();
        }
    }

    public static void main(String[] args) {
        SampleObject obj = new SampleObject();
        // Making the SampleObject eligible for garbage collection
        obj = null;
        // Requesting JVM to run Garbage Collector
        System.gc();
        System.out.println("Garbage Collection is performed by JVM");
    }
}
