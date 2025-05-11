package threads.Parrellelism;

import java.util.concurrent.*;

public class ParallelismExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int N = 100;
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cores);

        // split the range 1..N evenly into 'cores' chunks
        Future<Long>[] results = new Future[cores];
        int chunk = N / cores;

        for (int i = 0; i < cores; i++) {
            final int start = i * chunk + 1;
            final int end   = (i == cores - 1) ? N : (i + 1) * chunk;
            results[i] = pool.submit(() -> {
                long sum = 0;
                for (int j = start; j <= end; j++) {
                    sum += j;           // pure CPU work
                }
                return sum;
            });
        }

        long total = 0;
        for (Future<Long> f : results) {
            total += f.get();
        }
        pool.shutdown();

        System.out.println("Sum 1..N = " + total);
    }
}

