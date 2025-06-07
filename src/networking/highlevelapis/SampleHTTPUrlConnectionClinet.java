package networking.highlevelapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SampleHTTPUrlConnectionClinet {
    // http is hypertext transfer protocol ,mfor data communication between client and server,
    // http is stateless, http message contains reques and response with headers and body
    // verbs ,, https is a secure form of http ,, the http protocal has various methos or verbs
    // get is the default http method for http url connection

    public static void main(String[] args) {

        // since we are dealing with high level api ,, no need to create sockets for multiple connections since java does everything unde the hood
        // here thru hhtpurlconection we are sending data to http server
        try {
            URL url = new URL("http://localhost:8090/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json, text/plain, */*");
            httpURLConnection.setReadTimeout(20000);
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);
            if (responseCode != 200) {
                System.out.println("Error in url " + url);
                return;
            }
            printContents(httpURLConnection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printContents(InputStream inputStream) {

        System.out.println("input stream is " + inputStream);
        //inut stream -->  input data which will come in bytes
        //InputStreamReader wil convert the bytes to charaters
        //BufferedReader will read the characters in a line
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
