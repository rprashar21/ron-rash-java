package com.corejava.corejava.datatype;

public class SampleAutoBoxingWrapper {

    public static void main(String[] args) {

        // every primitive type have a wrapper class an it converts the primitive type to Object using the static method valueOf
          int myint=10;
        Integer integer = Integer.valueOf(myint); // autoboxing expilicitly

         Integer integer1 = myint; // autoboxing primtive to Object type

        Integer integer2 = new Integer(100);
        myint =integer2; // unboxing object to primitive
        // or
        int intValue = integer2.intValue(); // unnecesarry to do this


    }
}
