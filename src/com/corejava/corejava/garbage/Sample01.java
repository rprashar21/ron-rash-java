package com.corejava.corejava.garbage;

public class Sample01 {

    // java has three memory stack heap and metaspace
    // static variable are stored in metaspace
    // static object reference live on metaspace
    // all classes and all threads have access to the metaspace and that is why static variable can be accessed by any piece of code


    // importnace of intern

    public static void main(String[] args) {
        String one ="hello";
        String two ="hello"; // both of them are pointing to the same string object bcz of strings immutablity

        System.out.println(one.equals(two)); /// returns true
        System.out.println(one == two);  /// return true

        Integer i =76;
        String  three = i.toString().intern(); // will convert the put to string and intern method will put this into string contstant pool
        String four  = "76";

        System.out.println(three== four ); // this is because of the intern method
    }
}
