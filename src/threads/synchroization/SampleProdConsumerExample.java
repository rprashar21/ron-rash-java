package threads.synchroization;

import java.util.LinkedList;
import java.util.Queue;

public class SampleProdConsumerExample {

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>();
        // lets create 2 threads and start prodcuing and consuming
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    boundedBuffer.put(i);
                    System.out.println("Producer put item in the queue " + i);
                    Thread.sleep(3000); // sleep for 3 seconds
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    boundedBuffer.getItem();
                    Thread.sleep(3000); // sleep for 3 seconds
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); // we can use better logging in here
            }
        });

        producer.start();
        consumer.start();

        producer.join(); // main thread waits for both the threads to complete
        consumer.join();

        System.out.println("Process is over");
    }
}

class BoundedBuffer<T> {

    private final Queue<T> buffer = new LinkedList<T>();
    private final int capacity = 3;

    // produce calls to add items to the list
    public synchronized void put(T item) throws InterruptedException {

        //  System.out.println("Producer thread is producing " + Thread.currentThread().getName());
        while (buffer.size() == capacity) {
            wait(); // here the thread will go into the waiting state -- release the lock for this object and only resume when it gets notified
        }

        buffer.add(item);
        notifyAll(); // notifyAll -- wake up any threads waiting
    }

    // consumer calls this
    public synchronized T getItem() throws InterruptedException {

        //  System.out.println("Consumer thread is consuming " + Thread.currentThread().getName());
        while (buffer.isEmpty()) {
            wait(); // here the thread will go into the waiting state -- release the lock for this object and only resume when it gets notified
        }
        T item = buffer.remove();
        System.out.println("Consumer consumed item from the queue " + item);
        notifyAll(); // wake up any producers waiting
        return item;
    }
}
