package threads.CPUAndcores;


import java.util.concurrent.TimeUnit;

// Imagine you want to sum all the numbers from 1 to N and it’s so large that it really taxes the CPU
public class CpuBoundSum {

    public static void main(String[] args) throws InterruptedException {

        final long N = 200L;

        long startTime = System.currentTimeMillis();

        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores  in the machine : " + numThreads);

        Worker[] workers = new Worker[numThreads];
        Thread[] threads = new Thread[numThreads];

        long chunk = N / numThreads;

        System.out.println("chunk is : " + chunk);

        // Create and start threads
        for (int i = 0; i < numThreads; i++) {
            long start = i * chunk + 1;
            long end   = (i == numThreads-1) ? N : (i+1) * chunk;
            workers[i] = new Worker(start, end);
            threads[i] = new Thread(workers[i]);
            threads[i].start();
        }

        // Wait for all to finish
        long total = 0;
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            total += workers[i].partialSum;
        }

        System.out.println("Total sum = " + total);
        long diff = System.currentTimeMillis() - startTime;
        System.out.println("Total time taken = " + diff);
    }

    static class Worker implements Runnable {
        final long start, end;
        long partialSum;

        Worker(long s, long e) { start = s; end = e; }
        @Override
        public void run() {
            long sum = 0;
            for (long i = start; i <= end; i++) sum += i;
            partialSum = sum;
        }
    }
}
