package com.corejava.Files;

public class Strings {

    public static void main(String[] args) {

        String s = "";

        for(int i=0;i<4;i++)
        {
            s= s.concat(String.valueOf(i));
        }
        System.out.println(s);
    }
}
