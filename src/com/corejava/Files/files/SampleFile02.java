package com.corejava.Files.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class SampleFile02 {

    public static void main(String[] args) {
        // merge data of all txt files from resources folder
        // create a file
        //  use file to get names of all the files in output folder an the read and append in

        try {
            // this will creata file if not there
            PrintWriter printWriter = new PrintWriter("/Users/rohitprashar/Downloads/javares/src/main/resources/output.txt");

            // use file to get names of all the file in that folder
            File file = new File("/Users/rohitprashar/Downloads/javares/src/main/resources");
            final String[] listofFileNames = file.list();

            // read each file and write to the output text

            Arrays.stream(listofFileNames).forEachOrdered(fileName->{

                try {
                    BufferedReader bufferedReader
                            = new BufferedReader(new FileReader(new File(file,fileName)));
                     String line = bufferedReader.readLine();
                    while (line!=null)
                    {
                        printWriter.println(line);
                        line=bufferedReader.readLine();
                    }
                   bufferedReader.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

      printWriter.flush();
      printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
