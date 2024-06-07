package com.corejava.Files.basics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class PathSample {
    public static void main(String[] args) {

        getSomeAttributes();
        learnAboutDirectories();
        learnAboutExtraInfo();
    }

    private static void learnAboutExtraInfo() {
        System.out.println("-----------------------------");
        System.out.println("learn extra info");
        Path path = Paths.get("files/new/zzzz/samplepath.txt");
        try {
            Map<String, Object> map = Files.readAttributes(path, "*");
            map.entrySet().forEach(entry -> System.out.println(entry.getKey()+" "+entry.getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void learnAboutDirectories() {

        System.out.println("-----------------------------");
        System.out.println("learn about creating directories");
        Path path = Paths.get("files/new/zzzz/samplepath.txt");
        if (!Files.exists(path)) {
            // if the path does not exist create one
            System.out.println("path does not exist -- create a directory");
            try {
                // create a directory ,, then write somethin into that file
                //  Files.createDirectory(path.getParent()); // create a dir named zzzz
                Files.createDirectories(path.getParent()); // if there are multiple folder structure then use
                Files.writeString(path, "Hello sample path");
                System.out.println("Now reading from that file ");
                System.out.println(Files.readString(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void getSomeAttributes() {
        Path path = Paths.get("vagrant12/1231221312.txt"); // This file does not exist , it will not throw an error
        System.out.println("parent path " + path.getParent()); // this will show the current working directory
        System.out.println("absolute path " + path.toAbsolutePath()); //same
        System.out.println("Root " + path.getRoot()); // this means that this is relative path and there is no root directory

//        The path Paths.get("sample/somefile.txt") does not check for the file's existence.
//        It's just a representation of the path.
//                path.getParent() would return the directory part of the path, which is "sample" in this case.
//        path.toAbsolutePath() would return the absolute path, which is the full path from the root of the file system to the file.
//        path.getRoot() would return null in this case, as "sample/somefile.txt" is a relative path and does not include a root component.
    }
}
