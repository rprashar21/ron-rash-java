package com.corejava.Files;

import java.util.StringTokenizer;

public class StringTokenizerSample {

    public static void main(String[] args) {

        String str= "You,are,my,friend";
        // true will return the delimiter as well
        StringTokenizer stringTokenizer = new StringTokenizer(str,",",true);


        while (stringTokenizer.hasMoreTokens())
        {
            System.out.print(stringTokenizer.nextToken());
        }
    }
}
