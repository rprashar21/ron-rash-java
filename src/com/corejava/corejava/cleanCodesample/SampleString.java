package com.corejava.corejava.cleanCodesample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleString {

    //
    public static void main(String[] args) {
        String s1 = "Hello" + " World"; // use StringBuilder instead

        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(" World");
        String s = sb.toString();

        String s3 = "hello";
        String s4 = "hello".intern();
        // The intern() method is a method of the String class that returns a canonical
        // representation of the string. By using intern(), we can ensure that only one instance of a string is created,
        // even if the same string is created multiple times.
        // This can save memory by reducing the number of string objects created.

        // Use lazy initilization
        List<String> myList = null;
        getMyList(myList);

        
    }

    private static List<String> getMyList(List<String> myList) {
        if (myList == null) {
            myList = new ArrayList<>();
        }
        return myList;
    }

    public void writeFile(String filename, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        }
    }
    //By using try-with-resources, we can ensure that the FileWriter object is properly closed
    // and resources are cleaned up, without relying on the garbage collector to call finalize().
    // This can save memory by reducing the number of objects that need to be garbage collected.
}
