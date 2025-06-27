package threads.executors;

import java.util.*;
import java.util.concurrent.*;

public class RetryTaskExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<String> documentList = Arrays.asList("A", "B", "C", "D");

        List<Future<String>> futures = new ArrayList<>();

        for (String doc : documentList) {
            Future<String> future = executor.submit(() -> {
                int maxRetries = 3;
                int attempt = 0;
                while (attempt < maxRetries) {
                    try {
                        attempt++;
                        // Simulated external call
                        return callExternalService(doc, attempt);
                    } catch (RuntimeException e) {
                        System.out.println("Attempt " + attempt + " failed for " + doc + ": " + e.getMessage());
                        if (attempt >= maxRetries) throw e; // give up after retries
                        Thread.sleep(1000); // wait before retrying (polling delay)
                    }
                }
                return null;
            });
            futures.add(future);
        }

        // Handle responses
        for (Future<String> future : futures) {
            try {
                System.out.println("Result: " + future.get());
            } catch (ExecutionException e) {
                System.out.println("Final failure: " + e.getCause().getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();
    }

    // Simulated external call that fails for "C" randomly
    private static String callExternalService(String input, int attempt) {
        if (input.equals("C") && attempt < 3) {
            throw new RuntimeException("Service temporarily unavailable");
        }
        return input.toLowerCase();
    }
}

