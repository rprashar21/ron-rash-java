package com.corejava.Files.basics;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SampleFile01 {
    public static void main(String[] args) {

        //    " / " in frot of a file name like this measn it want the absolute path from the root folder
        // . indicates the current working directory

        /*
          root is the top level directory
        * A path is the filename or a directory
        * absolute path include the root directory starting by / or .
        * relative path is the path relative to the current working directory and does  not start with / but may start with . and then /
        *
        * */

        File file = new File(".","files/sampletext.txt"); // this is basically a file handler
        System.out.println("this will give the absolute path like doing pwd "+new File("").getAbsolutePath());
        System.out.println(file.getAbsolutePath()); // this get the present working directory
        if(!file.exists())
        {
            System.out.println("file does not exist ");
        }
        System.out.println("file exist");

          /*
         Path is an interface and not a class like File
         Paths class consists of static methods to return the instance of Path
        *
        When u use a File u get the instance with aconstructor to do something on that
        With NIO2 you first need to get an instance of Path
        * */

        Path path = Paths.get("files/test.txt"); // this is just a srting represenation
        System.out.println("Using path ");
        if(!Files.exists(path))
        {
            System.out.println("file does not exist ");
        }
        System.out.println("file exist");
    }
}
