package com.corejava.corejava.exeptionHandling.trywithResources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainEx {

    // try with resources
    // try{}catc(){}finally block
    /*
    * we need to close the resources manually
    * complexity increases
    * length of the code is more
    * try -- without catch or finally is invalid
    *
    *
    * try with reosurces is valid without cath or finally
    * a resource has to be both declared and initialized inside the try:
    * all these resources hould be autocloseable -- implements j.l.autocloseable
    * all the reference variables are imploctally final ,,hence we cannot reassign them
    * --advantage
    * automatically done by jvm
    * */

    public static void main(String[] args) throws IOException {
        TrySimulator trySimulator = new TrySimulator();
        trySimulator.withoutTryWithResources();

        // try with resources
        try(FileReader fr= new FileReader("");BufferedReader br = new BufferedReader(new FileReader(""))) {

        }
    }
}

class TrySimulator{

    void withoutTryWithResources() throws IOException {
        // without try resources
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            br.close();
        }
    }

    void withTryWithResources(){
        try(BufferedReader br= new BufferedReader(new FileReader("in.txt"));
        Scanner scanner = new Scanner(new File(""))
        )
        {
            // do ur stuff
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // multiple resources with multiple statements
        try (Scanner scanner = new Scanner(new File("testRead.txt"));
             PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
            while (scanner.hasNext()) {
                writer.print(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}