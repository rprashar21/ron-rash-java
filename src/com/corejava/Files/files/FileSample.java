package com.corejava.Files.files;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class FileSample {

    public static void main(String[] args) throws IOException {

        /*
        * Java file io is based on unix and everything is treated as a file.
        *
        *  file -- handler to create a file object or a file
        *
        * */

        // create a file
        File f = new File("/Users/rohitprashar/Downloads/javares/src/main/resources/xyz.txt");
        System.out.println(f.exists());

        // this createas a new file .. same for folder
        f.createNewFile();
        f.mkdir();

        // this will create a folder and a file in the this path under subfolder
        File file01 = new File("/Users/rohitprashar/Downloads/javares/src/main/resources","somefile.txt");
        file01.createNewFile(); // creATE A FILE IN THE FLDER SPECIFIED

        //check the number of files in a folder;
        File file1 = new File("/Users/rohitprashar/Downloads/javares/src/main/resources");
        final Optional<File> optionalFile = Optional.ofNullable(file1);
        optionalFile.ifPresent(optionalFile1-> Arrays.stream(optionalFile1.list()).forEachOrdered(s-> System.out.println(s)));
        String[] fileList = file1.list();

        Arrays.stream(fileList)
                .forEachOrdered(System.out::println);

//        filewriter --> object write character data to a file -- overrides the file
//        *   FileWriter fw = new FilWriter(file f) or --> if file is not present it will createa nd write to it --
//                *    instead of ovverriding we want to append thenwe have
//        *   fileWriter(string s, boolean append)

        // overrieds to a file -- char by char
        FileWriter fileWriter = new FileWriter("/Users/rohitprashar/Downloads/javares/src/main/resources/abct.txt");
        // appends to the file
        FileWriter fileWriter1 = new FileWriter("/Users/rohitprashar/Downloads/javares/src/main/resources/abct.txt",true);
        fileWriter.write("baad me");
        fileWriter.write("hello");
        fileWriter1.append(" this is appending");

        // if i dont close this file it will not write
        fileWriter.close();
        fileWriter1.close();


        // fileReader -- reads a file char by char
        // reads next char and return the unicode value hence integer value
        // when there is no next char returns -1 ,, needs typecasting to char when printing

        FileReader fileReader = new FileReader(("/Users/rohitprashar/Downloads/javares/src/main/resources/abct.txt"));
        int nextChar = fileReader.read(); // reads char by char and return unicode
        while(nextChar!=-1){
            System.out.print((char)nextChar);
            // go to next char
            nextChar=fileReader.read();
        }

    }
}
