package networking.highlevelapis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
    //We can create a simple Http server of our own, and then test a
    //few more methods on Http URL Connection. I could do something similar to what I
    //did in earlier videos, creating servers with Server Socket and Multi-threading.
    //Or I could use Server Socket Channels, with event driven techniques.

    // Java provides a very simple multithreaded http server
// HTTPServer is a class implements a simple HTTP server.
// A HttpServer is bound to an IP address and port number and listens for incoming TCP connections from clients on this address

    //  Backlog is the maximum number of queued up incoming
    //connections allowed on the listening socket. If you put a negative number or zero there, then
    //a system default is used

    // Steps 1. create an httpserver instance
    // 2. create a http context - a http context is a mappping between the uri path and the code that will handle the request .
    // this code os referred to as exchange handler , once created all the requests recived for the server will be hnalde by this exchange handler objject
    // the context is identified by the path
    //A context represents a URL path (or endpoint) on your HTTP server and the code that should handle requests to that path.
    // HttpExchange is an object that represents a single HTTP request and response cycle.
    // The request details: method, headers, URI, input stream (body)
    //The response controls: status code, headers, response body stream
    //  package that holds both the incoming request and the tools to send back a response.
    public static void main(String[] args) {

        try {
            // create an httpserver instance
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8090), 0);

            // we have created a context -- now whener we send a http request this will be handled by the exchange objects
            httpServer.createContext("/", exchange -> {
                String requestMethod = exchange.getRequestMethod();
                System.out.println("request method: " + requestMethod);

                // send back a response
                String response = getString();

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                byte[] responseBytes = response.getBytes();
                exchange.sendResponseHeaders(200, responseBytes.length);
                exchange.getResponseBody().write(responseBytes);
                exchange.close();
            });
            // start the simple httpserver
            httpServer.start();
            System.out.println("Server started on port 8090 ");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getString() {
        String response = """
                <HTML>
                <HEAD>
                <TITLE>Hello World</TITLE>
                </HEAD>
                <BODY>
                <H1>HTTP server </H1>
                <p>Hello World</p>
                <p>This is my local server hosted </p>
                </BODY>
                </HTML>
                """;
        return response;
    }
}
