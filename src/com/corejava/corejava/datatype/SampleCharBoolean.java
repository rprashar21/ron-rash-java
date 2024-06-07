package com.corejava.corejava.datatype;

public class SampleCharBoolean {

    /*
     *  char holds single character and in single quotes
     *  unicode is an international encoding standard by which each letter ,digit  symbol is assigned a unique numeric value
     *  3 ways to assing a value to a char
     *  literal char mychar ='D'
     *
     *  integer value -- char mychar =68
     *
     *  int and double are default data types
     * */

    public static void main(String[] args) {
        char firstChar = 'A'; char secondChar ='B';

        System.out.println(firstChar+secondChar); // integer value of A+B -- 68 + 69 =
        System.out.println(""+firstChar+secondChar); // AB + will be concatenation operaor bcz its after a string literal
    }
}
