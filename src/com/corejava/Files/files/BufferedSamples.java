package com.corejava.Files.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedSamples {
    public static void main(String[] args) {

        /*
        *  FileReader and writer is not preferred bcz of line separator manualyy which is varied from system to system
        *  Using filereader we read char by char --
        *  BuffereddReader/BufferedWriter  needs some reader/writer object to communicate with files
        *  we can read line by line and also char by char
        *  PrintWriter is the most enhanced to write character data -- we can wite any type of data
        *  BufferedReader is best to read data reads the entire line and by char
        *
        *
        *
        *   We use Streams to handle binary data like images and videos for those we use fileInpuTStream nad outputStream
        *   for characters or text from a file best writer us Print and reader is BufferedReader
        *
        *                                           Object
        *               writer                                          reader
        *            outputStreamWriter                                inputstreamreader
        *    printwriter,filewriter,Buferdwriter           bfrdr,filerdr
        *
        * */

        try( FileReader fileReader = new FileReader(("/Users/rohitprashar/Desktop/javares/src/main/resources/abc.txt"));
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            // reads an entire line and retursn a string

             String line = bufferedReader.readLine();
            while (line!=null)
            {
                System.out.print(line);
                line=bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
