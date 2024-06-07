package com.corejava.corejava.exeptionHandling.trywithResources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SampleThrowCheckedExceptin {
   // If we throw a checked exception using throw keyword,
    // it is must to handle the exception using catch block or the method must declare it using throws declaration.

    public static void main(String[] args) {

        try{
            fileUpload();
        }
        catch (FileNotFoundException e)
        {
            // we can terminate the program or handle it
            System.out.println(e.getLocalizedMessage());
        }
        finally {
        }

    }

    private static void fileUpload() throws FileNotFoundException {

        FileReader file = new FileReader("C:\\Users\\Anurati\\Desktop\\abc.txt");
        BufferedReader fileInput = new BufferedReader(file);


        throw new FileNotFoundException();

    }
}

