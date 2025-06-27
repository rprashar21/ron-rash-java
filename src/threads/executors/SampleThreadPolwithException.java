package threads.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SampleThreadPolwithException {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<String> documentList = Arrays.asList("A", "B", "C", "D");

        List<Future<String>> futures = new ArrayList<>();

        for (String s : documentList) {
            Future<String> future = executor.submit(() -> {
                if (s.equals("C")) throw new RuntimeException("Failed to process C");
                Thread.sleep(1000);
                return s.toLowerCase();
            });
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (ExecutionException e) {
                System.out.println("Error in task: " + e.getCause().getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
    }
}
