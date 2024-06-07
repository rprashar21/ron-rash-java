package com.corejava.corejava.datatype;

import java.math.BigDecimal;

public class SamplePrimitive {

    // 8 data types
    // whole number -- byte short int long
    // decimal -- float and double

    // single char -- char
    // boolean value -- boolean

    // then we have wrapper classes for these data types
    // objects


    // overflow situation -- when u put a value larger than the max value into an int you will create a situation of overflow

    // byte short int long has the smallest range to store whole numbers

    // java defaluts the type to an int

    // Floating type numbers have fractions -- float and double  also known as real number s

    // double is default type in java for decimal/ real numbers // they do have suffixes of D but that is optional
    // float has a suffix of f or F ,, this is not optional
    // double will represent decimal numbers more accurately
    // double is a beter choice than float bcz computers or java are functionall equivalent to deal with double
    // math funtions deal with double better than float
    // double is more precise handles larger numbers and its the default type for decimal/real numbers

    // float and double are good but should not be used when using precies calculations ,bcoz there is a limitation
    // on how floating point numbers are stored in java

    // USE BIGDECIMAL  class -- class is custom data type -- for precise calculations use BIGDECIMAL

    public static void main(String[] args) {

        // overflow situation
        int i = 1000000000;
        System.out.println(i);
        byte b = (byte) i; // casting the int value to byte -->
        System.out.println(b); // this will cause an overflow

        double numberOfPounds = 200d;
        System.out.println(numberOfPounds);

        double convertedToKilogrms = numberOfPounds * 0.45359237;
        System.out.println("double value "+convertedToKilogrms);

        BigDecimal bigDecimal = new BigDecimal(convertedToKilogrms);
        System.out.println("big decimal value "+bigDecimal);
        System.out.println(bigDecimal.setScale(2));

        // byte: 8-bit signed integer size  i byte 8 bits
        byte myByte = 127;  // range -128  - 127
        // max value -127 and min value -128
      //  byte b12= 128; // possible ost of precision  throws a compiler error

        // short: 16-bit signed integer
        short myShort = 32000;

        // int: 32-bit signed integer
        int myInt = 1000000;

        // long: 64-bit signed integer
        long myLong = 1234567890123L;

        // float: 32-bit floating-point number
        float myFloat = 3.14159f;

        // double: 64-bit floating-point number
        double myDouble = 2.71828;

        System.out.println("byte: " + myByte);
        System.out.println("short: " + myShort);
        System.out.println("int: " + myInt);
        System.out.println("long: " + myLong);
        System.out.println("float: " + myFloat);
        System.out.println("double: " + myDouble);

        // byte 1 byte -128 -127
        // short 2 byte -2^15
        // int 4 byte 32 bit signed
        // long 8 byte
        // float is 4 byte
        // double is 8 byte
        // char 2 bytes


        }
}
