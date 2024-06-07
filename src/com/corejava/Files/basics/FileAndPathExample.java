package com.corejava.Files.basics;

import static java.nio.file.Files.writeString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileAndPathExample {

    public static void main(String[] args) {

        // useFile("testingFile.txt");
        usePath("testingFile.txt");
    }

    private static void usePath(final String fileName) {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            // delete the file
            System.out.println("deleting the file ");
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                System.out.println("file - path is created ");
                System.out.println("Now i will write somethin in that file ");
                if (Files.exists(path)) {
                    System.out.println(">> Writing to a file  ");
                    writeString(path, "hello world /n kuch bhi bolte "
                    );
                }
                System.out.println("Now reading from the file");
                System.out.println("--------------------------");
                String s = Files.readString(path);
                System.out.println(s);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void useFile(final String fileName) {

        // find a file exists if not delete that file and create a new one

        File file = new File(fileName);
        boolean fileExists = file.exists();

        if (fileExists) {
            // delete the file
            System.out.println("deletin the file ");
            file.delete();
        }
        if (!fileExists) {
            // create a new file
            try {
                file.createNewFile();
                System.out.println("file created");
                if (file.canWrite()) {
                    System.out.println("Would write in the file");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
