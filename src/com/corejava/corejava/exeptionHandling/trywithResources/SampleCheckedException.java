package com.corejava.corejava.exeptionHandling.trywithResources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SampleCheckedException {

    // checked exception or compile time exceptions can be handled using try catch or throws keyword

    public static void main(String[] args) {
        PrintWriter pw = null;
        try{
            pw = new PrintWriter("abc.txt");
        }catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            pw.close();
        }
        System.out.println("file saved successfully");
        int playerScore = getPlayerScore("ac.txt");
        System.out.println("palyer score is "+playerScore);
    }
    public static int getPlayerScore(String playerFile) {
        try (Scanner contents = new Scanner(new File(playerFile))) {
            return Integer.parseInt(contents.nextLine());
        } catch (IOException | NumberFormatException e) {
         //   System.out.println("Failed to load score!");
            System.out.println("Failed To load score becoz of the exception "+e.getMessage());
            return 0;
        }
        finally {
            System.out.println("hello finally");
        }
    }
}
