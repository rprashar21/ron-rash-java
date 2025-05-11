package threads.Parrellelism;

import static threads.complteablefuture.Delay.*;

public class ConcurrenyExample {

    private static String result = "";

    private static void hello() {
        delay(500);
        System.out.println(Thread.currentThread().getName());
        result = result.concat("Hello");
    }

    private static void world() {
        delay(500);
        System.out.println(Thread.currentThread().getName());
        result = result.concat(" World");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> hello()); // create a  new thread  and call the staitc method
        Thread t2 = new Thread(() -> world()); // create a  new thread  and call the staic method

        t1.setName("t1  thread");
        t2.setName("t2 thread");
        // starting the threads
        t1.start();
        t2.start();

        // there are 2 threads

        // asking the main thread to wait until the 2 threads have finsished
        t1.join();
        t2.join();

        System.out.println("result is " + result);
    }
}

