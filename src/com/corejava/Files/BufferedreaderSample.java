package com.corejava.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class BufferedreaderSample {
    public static void main(String[] args) throws IOException {
        readAndCountFromAPDFUsingBufferedReader();
    }

    private static void readAndCountFromAPDFUsingBufferedReader() throws IOException {
        StringBuilder stringBuilder
                = new StringBuilder();
        File file = new File("/Users/rohitprashar/Desktop/con.txt");
        if(!file.exists())
            file.createNewFile();
        //   reads char by chra
        try (FileReader fileReader = new FileReader("/Users/rohitprashar/Desktop/con.csv")) {

            int line;
            stringBuilder = new StringBuilder();

            while ((line = fileReader.read()) != -1) {

                // count the characters
                stringBuilder.append((char) line);
            }
        }
        FileInputStream fileInputStream = new FileInputStream(file);

        while(fileInputStream.read()!=-1)
        {
            int read1 = fileInputStream.read();
            System.out.print((char)read1);

        }

    }


}
