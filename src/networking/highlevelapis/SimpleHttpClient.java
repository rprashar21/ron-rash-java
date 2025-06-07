package networking.highlevelapis;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.Stream;

public class SimpleHttpClient {

    // since httpurlconnection class is ok for reading htto web server it was not good enoug so javas hhtpclient was introduced in 2015 in jdk11
    // Also the new http/2 protocol was introduced in 2015 is faster and efficient

    ///  why do we use hhtpclinet ?? --> it supporst http/2 protocol cleaner faster than httprulconnection class , aysnc, modern secure
    /// HttpClient is more modern, easier to use, and supports asynchronous and HTTP/2 out of the box.
    ///
    ///  When You Should Use HttpClient
    /// When building REST clients or API consumers.
    ///
    /// For internal service communication in microservices.
    ///
    /// If you're writing CLI tools that need to fetch web data.
    ///
    /// For better performance using HTTP/2 and connection reuse.
    ///
    /// When you need non-blocking async HTTP calls.

    public static void main(String[] args) {

        // url
        //
        try {
            //Creating a URL object to target your local HTTP server on port 8090
            URL url = new URL("http://localhost:8090"); // conncetc to my local httpserver

            // this httpclient -- his client supports HTTP/2, asynchronous requests, better configuration, and timeouts.
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(url.toURI())
                    .headers("Accept", "application/json", "Accept", "text/html")
                    .timeout(Duration.ofSeconds(20))
                    .build();

            // Sends the request synchronously (blocks until response is received from the server).
            HttpResponse<Stream<String>> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofLines());

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
                return;
            }
            System.out.println(response.body());
            response.body().filter(line -> line.contains("<H1>") || line.contains("<p>"))
                    .map(line-> line.replaceAll("<H1>","").strip())
                    .map(line-> line.replaceAll("<p>","").strip())
                    .forEach(System.out::println);

        } catch (IOException | URISyntaxException | InterruptedException e) {

        }


    }
}
