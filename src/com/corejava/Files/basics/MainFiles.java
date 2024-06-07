package com.corejava.Files.basics;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainFiles {
    /*
    * There are 2 types of Stream
ByteStream  and CharacterStream
InputOrOutputStrea,
ByteStream --> read/write bytes like images text videos audio -- no conversion of bytes to characters
Classes - java.lang.object
              inputStream --(read bytes from a file)
              BufferedInputStream -(buffers bytes from a file) when we want to read large files
              DataInputStream - read primitive datatypes in java
              * ObjectInputStream - deserializes primitive data type
Encoding: It's a way of converting characters into bytes.
* Different encodings have different ways of representing characters. UTF-8, ISO-8859-1, and US-ASCII are examples of character encodings.

* * Use byte streams (FileInputStream/FileOutputStream) for binary data like images, audio, etc.
Use character streams (FileReader/FileWriter) for text data. They handle encoding automatically.
Use BufferedReader for efficient reading of characters, arrays, and lines.
Use InputStreamReader and OutputStreamWriter for converting byte streams to character streams with a specified charset.
    * */
    public static void main(String[] args) {
        // here we are supposedly reading an image or video or text
        // we are streaming into bytes
        // we convert that to string which is immutable and we can carry that across the application and store it somewhere
        byteStreamMethod();
        characterStreamMethod();
    }

    private static void characterStreamMethod()  {
        System.out.println("----------> Entering into the world of Character Streaming <-------------");
    // these belong to the character
// bufferedReader --> buffers characters from a file
// filerReader --> read char by char
// InputStreamReader -->  is used to read bytes from an input stream and convert them into characters using a specified charset.
// StringReader -- same for writers belong to the character streamings
        try{
            File file  = new File("input.txt");
            if(!file.exists())
                file.createNewFile();

            // reading bytes of data
            FileInputStream fileInputStream = new FileInputStream(file);
            // this input Stream reader will convert bytes to characters and specify the encoding
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,StandardCharsets.UTF_8);
            // bufferedReader filerReader InputStreamReader StringReader -- same for writers belong to the character streamings
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine())!=null)
            {
                sb.append(line);
            }
            System.out.println(sb);
        }catch (IOException e){

        }

    }

    private static void byteStreamMethod() {
        System.out.println("----------> Entering into the world of Byte Streaming <-------------");
        try{
            // this is just a file handler
            File file  = new File("input.txt");
            if(!file.exists())
                file.createNewFile();
            // now i will stream the contents of this file into bytes
            FileInputStream fis = new FileInputStream(file);
            // if the this will stream byte by byte from the file

            // Now if we want to buffer stream large files we use this or if this is a text file we can convert it to characters using
            // InputStreamReader from the reader/wrtier character stream hierarchy and then
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            int byteDate;
            StringBuilder sb = new StringBuilder();
            while((byteDate = bufferedInputStream.read())!=-1)
            {

                // this is byte data
                sb.append((char)byteDate);
            }
            System.out.println(sb);
        }catch (IOException e){
            System.out.println("file creation failed");
        }
    }

}
