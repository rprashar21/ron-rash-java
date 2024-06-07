package threads.producerconsumer;

import java.util.ArrayList;

public class Processor {

    // wait and notify  using the producer and consumer
    private ArrayList<Integer> list = new ArrayList<>();
    private static final Integer UPPER_LIMIT = 5;
    private static final Integer Lower_LIMIT = 0;
    private final Object lock = new Object();
    private int value =0;


    public void producer() throws InterruptedException {


        synchronized (lock) { // lock of this object
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    // list is full then it has to wait for the consumer to be free
                    System.out.println("waiting for the consumers to consume data..."+Thread.currentThread().getName());
                    // release the lock and go to waiting state
                 lock.wait();
                }else {
                    System.out.println("Adding Data to the list"+Thread.currentThread().getName()+value);
                    list.add(value++);
                    // we can call notify the other thread which will be notified only when it is in waiting state
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {


        synchronized (lock) {
            while (true) {
                if (list.size() == Lower_LIMIT) {
                    // list is full then it has to wait for the consumer to be free
                    System.out.println("Waiting for Producer to input data "+Thread.currentThread().getName());
                    // release the lock and go to waiting state
                    lock.wait();
                }else {
                    System.out.println("start consuming data  " +Thread.currentThread().getName());
                    list.remove(list.size()-1);
                    // we can call notify the other thread which will be notified only when it is in waiting state
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
