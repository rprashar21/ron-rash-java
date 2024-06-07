package com.corejava.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DifferenceBetweenTheseStreams {
    /*
    * Here are the steps and  code sample of reading InputStream as String in Java :

Step 1: Open FileInputStream to read contents of File as InputStream.
Step 2: Create InputStreamReader with character encoding to read byte as characters
Step 3: Create BufferedReader to read file data line by line
Step 4: Use StringBuilder to combine lines
    *
    *https://www.java67.com/2014/05/3-examples-to-read-inputstream-as-String-Java-Guava-Commons.html
    *
    * */
    public static void main(String[] args) throws FileNotFoundException {
        readFile();

    }

    private static void readFile() throws FileNotFoundException {

        try(FileInputStream  fis = new FileInputStream("/Users/rohitprashar/Desktop/con.txt")){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void verifyGeneratedCsvDocumentIsValid(final String filePath) {
        try {
            // this just a file handler --
            File file = new File(filePath);
            // fileINputStream read bytes from a file
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            // BuffereReade falls under the characters stream -- uses InputStreamReader which will conver the bytes to charcters
            // bufferedReads is used to read large chunkcs of data
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            StringBuilder actualCsvContentBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                actualCsvContentBuilder.append(line).append("\n");
            }
            String actualCsvContent = actualCsvContentBuilder.toString();
            String expectedCsvContent = getExpectedCsvContent(filePath);
            if (!actualCsvContent.equals(expectedCsvContent)) {
                throw new AssertionError("The actual CSV content does not match the expected content.");
            }

            br.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while verifying the generated CSV document.", e);
        }
    }

    private String getExpectedCsvContent(final String filePath) {
        return "";
    }
}
