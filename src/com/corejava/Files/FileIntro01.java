package com.corejava.Files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileIntro01 {

    //n Java, we read data from file or socket using InputStream and write data using OutputStream.
    // Inside Java program, we often use String object to store and pass file data,
    // that's why we need a way to convert InputStream to String in Java


    // FileInputStream	Opens the file: like opening a pipe from disk
    // InputStreamReader	Converts bytes to characters (e.g. UTF-8 to readable text)
    // BufferedReader	Reads whole lines & caches data to avoid reading one char at a time

    //FileInputStream → to get bytes from the file
    //
    //InputStreamReader → to turn bytes into characters (text)
    //
    //BufferedReader → to read efficiently, one line at a time

    // why there is not one class
//    Files store data as bytes (FileInputStream)
//
//    You want text, so need to decode bytes (InputStreamReader)
//
//    You want to read lines or blocks efficiently (BufferedReader)
//
//    Each class follows the Single Responsibility Principle: do one thing well, and stack them to build powerful behaviour

    public static void main(String[] args) {
                                           // this is the absolute path
        try(InputStream inputStream = new FileInputStream("/Users/rohitprashar/Desktop/ronrashjav/src/com/corejava/Files/files/finance.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            System.out.println(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
