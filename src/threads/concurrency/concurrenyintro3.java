package threads.concurrency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class concurrenyintro3 {

    // defintion of async --> You kick off an operation and immediately get control back;
    // when the work completes, you’re “called back” (via callback, future, promise) rather than blocking the current thread.

    //vAsynchronous (Async)
    public static void main(String[] args) throws IOException, InterruptedException {



        // Key he main thread never blocks on send(). It “submits” the work and carries on
        // Java: using CompletableFuture for async I/O
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // simulate blocking I/O (e.g. HTTP request)
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create("https://example.com")).build();
            try {
                return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
// main thread continues immediately:
        System.out.println("Doing other work");
// Later, when ready:
        future.thenAccept(body -> System.out.println("Response length: " + body.length()));

        // Multithreading
        // u explicitly create or use multiple threads of control in the same process—each one has its own call stack but shares heap memory.
        // Real-world analogy: Two chefs in the same kitchen preparing two different dishes independently using same stove maybe.

        DataProcessor t1 = new DataProcessor("dp1");
        DataProcessor t2 = new DataProcessor("dp2");

        //  you see two distinct Java Thread objects, each running run() concurrently (or even in parallel).


    }
}

class DataProcessor{
    private Thread thread;
    private String name;

    public DataProcessor(final String name) {
        this.name = name;
        thread = new Thread(() -> {
            System.out.println(this.name + " start");
            // may be one thread is waiting some i/o call network or sleep
        });
        thread.start();
    }
}


