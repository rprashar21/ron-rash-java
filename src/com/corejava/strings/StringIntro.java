package com.corejava.strings;

public class StringIntro {

    // when to use string vs string buffer vs string builder
    // fastest is stringbuilder -- in a single threaded application -- mutable -- when u do a lot of midfications
    // string immutable ,slow , not for modifications , not thread safe
    // stringbuffer is an alternative to stringbuilder but its slower than stringbuilder but its thread safe becoz its synchronized

   // Use StringBuilder when doing lots of appends or dynamic construction of strings — it’s memory and CPU efficient.


}
class StringPerformanceTest {
    public static void main(String[] args) {
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(i);
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
