package com.corejava.Files.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterSample {
    // write character data from 2 files into one

    public static void main(String[] args) throws FileNotFoundException {

      //  abct.tx and xyz.txt --> input01.txt

        PrintWriter printWriter = new PrintWriter("/Users/rohitprashar/Downloads/javares/src/main/resources/input01.txt");

        // reading best is BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/rohitprashar/Downloads/javares/src/main/resources/abct.txt"));

        try {
            String line = bufferedReader.readLine();
            while(line!=null)
            {
                printWriter.println(line);
                line =bufferedReader.readLine();
            }

            bufferedReader = new BufferedReader(new FileReader("/Users/rohitprashar/Downloads/javares/src/main/resources/xyz.txt"));
            line = bufferedReader.readLine();
            while(line!=null)
            {
                printWriter.println(line);
                line =bufferedReader.readLine();
            }
            printWriter.flush();
            bufferedReader.close();
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
