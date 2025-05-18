package threads.synchroization;

import static java.lang.Thread.sleep;

public class DeadLock {

    /*
    *  when 3 threads are waiting for each other forever then its such type of infinite waiting is called deadlock
    *  Deadlock occurs bcz of synchronization
    * Two or more threads each grab two locks in opposite order—once they each hold one lock and block on the other, neither can proceed.
    *
    *
    *
    * */
    private static final Object object1 = new Object();
    private static Object object2 = new Object();
    public static void main(String[] args) {


        // create 2 threads and each thread gets the lock of the  2 objects

       Thread t1 = new Thread(() -> {
           synchronized (object1) {
               System.out.println(Thread.currentThread().getName() + " has lock of object1");
             sleep(); // wait for thread 2 get the lock of object2
               System.out.println("Thread-1: Waiting for lock of object 2...");
               synchronized (object2) {
                   System.out.println(Thread.currentThread().getName() + " has lock of object2");
               }
           }
       });
       t1.setName("Thread-1");

        Thread t2 = new Thread(() -> {
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + " has lock of object2");
                sleep(); // wait for thread 2 get the lock of object2
                System.out.println("Thread-2: Waiting for lock of object 1...");
                synchronized (object1) {
                    System.out.println(Thread.currentThread().getName() + " has lock of object1");
                }
            }
        });
        t2.setName("Thread-2");
        t1.start();
        t2.start();

        System.out.println("Main thread started");

    }

    private static void sleep(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
