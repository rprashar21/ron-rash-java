package threads.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadexecutorsSample {
   // The Executor framework is the modern, preferred way to manage thread pools and asynchronous tasks.

 // The s ExecutorService, Future, Callable, CompletableFuture
    //Offer more flexible task submission, result handling, and lifecycle management.

    ExecutorService executor = Executors.newCachedThreadPool();


}

class CallingDatabaseAction implements Callable<String> {

    @Override
    public String call() throws Exception {
    return getConnection();
    }

    private String getConnection() {

        return "jdbc:postgresql://localhost:5425/sjpviewstore?user=pgreadonly&password=pgreadonly&sslmode=require ";
    }
}

