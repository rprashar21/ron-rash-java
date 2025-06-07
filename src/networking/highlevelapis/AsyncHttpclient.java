package networking.highlevelapis;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncHttpclient {


    //steps
    // url ---

    ///  create a http cleint
    // create a request object
    // send the request object to the server
    // handle the response
    public static void main(String[] args) {


        try {
            URL url = new URL("http://localhost:8090");

            HttpClient client = HttpClient.newHttpClient();
            System.out.println("async client running");
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(url.toURI())
                    .headers("Accept", "application/json")
                    .build();

            // this a synchronous call -- change this to aysnce
//            HttpResponse<String> response =
//                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // asnc call -- means i is a non blocking call itw ill call and not wait
            CompletableFuture<HttpResponse<String>> aysncResponse
                    = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());



            while (!aysncResponse.isDone()) {
                System.out.print(".");


            }
            // this is a blocking call
            HttpResponse<String> stringHttpResponse = aysncResponse.get();
            handleResponse(stringHttpResponse);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleResponse(final HttpResponse<String> response) {
        int statusCode = response.statusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Unexpected response code: " + statusCode);
        }
        System.out.println(response.body().toString());
        System.out.println();
    }
}
