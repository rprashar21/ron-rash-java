package com.corejava.Files.basics.difference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CharacterStreamExample {

    ///  to read a file char by chra we can use FileReader
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("src/com/corejava/Files/basics/sample.txt");
            int ch;
            while ((ch = fileReader.read()) != -1) {
              //  System.out.print((char) ch); // print the characters , this is slow lot of io operations
            }
            // reading file useing buffered reader is effiecint as it reads entire lines
            // loads a chunk of data

            fileReader.close();

            readUsingBufferedReader();

        } catch (IOException e) {
        }
    }

    private static void readUsingBufferedReader() {

        try (BufferedReader br = new BufferedReader(new FileReader("src/com/corejava/Files/basics/difference/sample.txt"))) {

            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line +"\n");
            }
            System.out.println(builder.toString());

        } catch (Exception e) {

        }
    }

    // if want to read using bytes say u have a file-- stored in disk like 0s and 1s
    // and you he InputStreamReader acts like a translator 👂 who listens to bytes and speaks characters.
//    File (on disk, bytes)
//    ↓
//    FileInputStream (reads bytes)
//    ↓
//    InputStreamReader (translates bytes → characters)
//    ↓
//    BufferedReader (reads full lines efficiently)
    private static void readUsingInputStreamReader() {
       // step 1 to read the filde for the disk
        // FileInputStream Reads bytes from file
        try(FileInputStream fileInputStream = new FileInputStream("src/com/corejava/Files/basics/sample.txt")){

            // convert the bytes to characters
            InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
           // reads characters from line by line
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // reading line by line
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        FileInputStream brings the data,
//        InputStreamReader makes it readable,
//        BufferedReader makes it fast and smooth.

    }

}
