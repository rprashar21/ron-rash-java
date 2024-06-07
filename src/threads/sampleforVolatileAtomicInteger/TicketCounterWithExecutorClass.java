package threads.sampleforVolatileAtomicInteger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketCounterWithExecutorClass {

    private static AtomicInteger ticketsSold = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        fixedThreadPool.submit(new TicketSelling());
        fixedThreadPool.submit(new TicketSelling());
        // shutdown the exector whene the tasks are submitted

        fixedThreadPool.shutdown();
        if(ticketsSold.get()==20)
        System.out.println("Total tickets sold: " + ticketsSold.get());

    }

   static class TicketSelling implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<20;i++)
            {
                // multiple threads access this atomic varibale but since this is atomic any transaction will be done in isolation
                System.out.println(Thread.currentThread().getName());
                ticketsSold.incrementAndGet();
            }
        }
    }

}

/*
* Race conditions: Race conditions can occur when multiple threads simultaneously access and modify a shared variable without proper synchronization.
* In the context of a ticket counter, two threads might read the current ticket count, increment it, and write it back simultaneously.
* This can lead to lost updates and incorrect counts.

Data corruption: When multiple threads update a non-atomic variable simultaneously, data corruption can occur.
* For example, if two threads simultaneously attempt to increment the ticket count,
* they may both read the same value, increment it, and then overwrite each other's updates, resulting in lost increments and an incorrect final count.

By using an atomic integer, you ensure that each increment operation is atomic,
* eliminating the possibility of race conditions and data corruption.
* Atomic operations provided by atomic integers guarantee that no two threads can interfere with each other during the update process.
*
* */