package threads.CPUAndcores;


import java.sql.SQLOutput;

public class LockBoundCounter {
    private long counter = 0;

    public  void increment() {
        System.out.println("Thread exexuting the lock --> "+Thread.currentThread().getName());
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        LockBoundCounter c = new LockBoundCounter();

        int threads = 8;
        // create an array of threads here we create a
        Thread[] ts = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            ts[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    c.increment(); // synchronized
                }
            });
            ts[i].start();
        }
        for (Thread t : ts) t.join();
        System.out.println("Count = " + c.counter);
    }
}
//What it does: Eight threads all try to increment the same counter under a synchronized lock.
//
//Why extra cores won’t help:
//
//Only one thread at a time can hold the lock.
//
//The other 7 sit blocked inside the JVM’s lock‐waiting queue.
//
//More cores only mean more threads contending, and you actually slow down because of context switches.
