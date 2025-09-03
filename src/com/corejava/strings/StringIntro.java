package com.corejava.strings;

import java.util.StringJoiner;

public class StringIntro {

    // when to use string vs string buffer vs string builder
    // fastest is stringbuilder -- in a single threaded application -- mutable -- when u do a lot of midfications
    // string immutable ,slow , not for modifications , not thread safe
    // stringbuffer is an alternative to stringbuilder but its slower than stringbuilder but its thread safe becoz its synchronized

   // Use StringBuilder when doing lots of appends or dynamic construction of strings — it’s memory and CPU efficient.


}
class StringPerformanceTest {
    public static void main(String[] args) {

      //  In a single expression, the compiler uses a StringBuilder under the hood:
        String s = "a" + "x" + "b";


// Java creates a new StringBuilder, appends result and i, then calls .toString()
// So for 5 iterations → 5 new StringBuilders + 5 new String objects
        String result = "";
        for (int i = 0; i < 5; i++) {
            result = result + i;   // using +
        }
        System.out.println(result);

        // this is effeicent becoz only one stringbuilder is used
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i);
        }
        String result1 = sb.toString();
        System.out.println(result1); // "01234"

        // we can also use String JOiner
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < 5; i++) {
            joiner.add(String.valueOf(i)); // internally this use Integer.toString(i)
        }
        System.out.println(joiner); // "0, 1, 2, 3, 4"

        // functional Style programming




        long startTime, endTime;

        // String (immutable)
        startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String time: " + (endTime - startTime) + "ms");

        // StringBuilder
        startTime = System.currentTimeMillis();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb1.append(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endTime - startTime) + "ms");

        // StringBuffer
        startTime = System.currentTimeMillis();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            sf.append(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endTime - startTime) + "ms");
    }
}
