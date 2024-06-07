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
        Thread heloo = new Thread(() -> hello()); // create a  new thread  and call the staic method
        Thread world = new Thread(() -> world()); // create a  new thread  and call the staic method

        heloo.setName("hello  thread");
        world.setName(" world thread");
        // starting the threads
        heloo.start();
        world.start();

        // there are 2 threads

        // asking the main thread to wait until the 2 threads have finsished
        heloo.join();
        world.join();

        System.out.println("result is " + result);
    }
}

