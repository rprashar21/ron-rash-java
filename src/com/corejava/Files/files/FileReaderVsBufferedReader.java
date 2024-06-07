package com.corejava.Files.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class FileReaderVsBufferedReader {


    // check if file exist?
    // if not create a file
    // write some content into it and the read it in another file
    // filereader reads char by char or eniter line ,, return the byte code value

    public static void main(String[] args) throws IOException {

        // file is handler for file objects
        File file = new File("/Users/rohitprashar/Desktop/abcdef.txt");
        boolean file1 = file.isFile();
        System.out.println(file1);
        if(file.exists())
        {
            boolean newFile = file.createNewFile();
            System.out.println(newFile);

            try( FileWriter fileWriter = new FileWriter(file,true);

            ){
                fileWriter.write("swati ");
                fileWriter.write("rohit ");
                fileWriter.write("prashar ");
                fileWriter.write("yoda ");
                fileWriter.close();
               readFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

           sb.append(br.readLine());
            System.out.println(sb);
        }catch (IOException e){

            System.out.println(e);
        }

    }
}
