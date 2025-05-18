package threads.volatilevsatomicityvssynchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleAtomicExample {

    private static AtomicInteger ticketsSold = new AtomicInteger(0);


    public static void main(String[] args) {
        Runnable ticketSoldTasks =()->{

            while (ticketsSold.get() <=10) {
                System.out.println("Ticket sold: " + ticketsSold.getAndIncrement());

                if(ticketsSold.get() == 10) {
                    System.out.println("All tickets are sold ie is  " + ticketsSold);
                    break;
                }

            }
        };

        // Let create booth where tickes will be sold
        ExecutorService service = Executors.newCachedThreadPool();


        System.out.println("ticketsSold: " + ticketsSold);
        for (int i = 0; i < 5; i++) {
            service.submit(ticketSoldTasks);
        }

        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ticketsSold: " + ticketsSold);
    }
}
