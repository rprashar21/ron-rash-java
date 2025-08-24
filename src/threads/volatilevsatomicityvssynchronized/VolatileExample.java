package threads.volatilevsatomicityvssynchronized;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileExample {

    private volatile int counter =0;

    // synchronized so one thread at a time
    public synchronized int incrementAndGet() {
        counter++;
        return counter;
    }
    public boolean isReady() {
        // no need to synchronize reads for visibility if counter is volatile
        return counter >= 10;
    }

    public static void main(String[] args) {
        VolatileExample volatileExample = new VolatileExample();
        // A CountDownLatch is a simple thread‐coordination aid in java.util.concurrent that allows one or
        // more threads to wait until a set of operations being performed in other
        // threads completes (or, in our example, until a “ready” signal is given).
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // we want threads to simulataneoulsy execute the increemnt ad get code
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable task =  () -> {

            try{
                System.out.println(Thread.currentThread().getName() + ": Incrementing counter");
                // each worker thread calls this and goes to the waiting state

                countDownLatch.await(); // whenever a thread executes this it will go in the waiting stage
                while(!volatileExample.isReady()) {
                    int currentcount = volatileExample.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + ": " + currentcount);
                }

            }catch (Exception e) {

            }
        };

        // now im execurting this task
        executorService.execute(task);
        executorService.execute(task);

        // let both threads run “concurrently” this wil make the countdown == 0
        // the latch’s count is decremented by 1. If it reaches zero, all threads blocked in await() are released at once.
        countDownLatch.countDown();

        executorService.shutdown();

    }
}
