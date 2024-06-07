package com.corejava.corejava.datatype;

import java.math.BigInteger;

public class SampleBigInteger {

    // you should work with BigInteger when you work with extremly large int values and cannot fit into int byte long
    // it useful for precise calculation , currency conversions ,string conervsion where integer values are huge
    // they can be converted to string representation into mathemateical objects
    // for large numbers whose value cannot be fit into primitive types  like int or long
    //  increased memory consumption and potentially slower performance
   // Autoboxing is converting from primitive to object like int to Integer
    // unboxing is object to primitive

    public static void main(String[] args) {
        Integer myInt = 34; //-- Integer.valueOf(34) -- autoboxing
        int intvale= myInt; // unboxing -- myInt.intValue

        Long mylOng =129232321212L;
        BigInteger bigInteger1 = new BigInteger("123456789012345678901234567890");
        BigInteger bigInteger2 = new BigInteger("987654321098765432109876543210");

        BigInteger sum = bigInteger1.add(bigInteger2);
        BigInteger product = bigInteger1.multiply(bigInteger2);

        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);

    }
}
