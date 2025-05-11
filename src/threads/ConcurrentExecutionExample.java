package threads;

import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentExecutionExample {

    public static void main(String[] args) {
        // Create two threads
        Thread thread1 = new Thread(new CountingTask("Thread 1"));
        Thread thread2 = new Thread(new CountingTask("Thread 2"));

        // Start the threads
        thread1.start();
        thread2.start();
    }

    // A simple task that counts from 1 to 5
    static class CountingTask implements Runnable {
        private final String threadName;

        public CountingTask(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            AtomicInteger count = new AtomicInteger(0);


        }
    }
}
// Concurreny -- rogrmultiple tasks pess over  same time ,, not at the same instant
// Concurrency means “multiple tasks in progress at overlapping times.”
//
//Your two loops are concurrent: they both start, both can make progress, and may interleave.
