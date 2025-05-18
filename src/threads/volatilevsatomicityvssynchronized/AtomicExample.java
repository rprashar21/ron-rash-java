package threads.volatilevsatomicityvssynchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class AtomicExample {
    private static final AtomicInteger ticketsSold = new AtomicInteger(0);
    private static final int MAX_TICKETS = 10;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService sellingPool = Executors.newFixedThreadPool(5);

        Runnable boothTask = () -> {
            while (true) {
                int ticketNumber = ticketsSold.incrementAndGet();

                // Sold a valid ticket?
                if (ticketNumber <= MAX_TICKETS) {
                    System.out.println(
                            Thread.currentThread().getName() +
                            " sold ticket #" + ticketNumber
                    );
                    // Announce “houseful” exactly once
                    if (ticketNumber == MAX_TICKETS) {
                        System.out.println("Houseful! All tickets are sold.");
                    }
                } else {
                    // No more tickets — this thread is done
                    break;
                }
            }
        };

        // Launch 5 concurrent booths
        for (int i = 0; i < 5; i++) {
            sellingPool.execute(boothTask);
        }

        sellingPool.shutdown();
        sellingPool.awaitTermination(1, TimeUnit.MINUTES);
    }
}

