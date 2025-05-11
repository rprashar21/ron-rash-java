package threads.synchroization.prod;

// shared object
class Counter {
    private int count;
    // this will check if the producers has produce and consumer has consumed
    private boolean shouldProduce = true;

    // this method will be used by the producer to produce
    synchronized public void setCount(int count) throws InterruptedException {

        // before producing inside the counter you the producer should check the flag if its false then produce
        if (shouldProduce == false) {
            System.out.println("Producer thread is gone to waiting queue");
            wait(); // this wait does 2 thing it puts the currecnt thread in the waiting quueue and this will release the lock
            /// wait a method of the object class and hence it is called directly
            // now to come out of wait it will come out by using notify
        }
        // it will be able to produce becoz now the shouldProduce is true ,, it soutside the if
        this.count = count;
        // and now change the shouldProduce to false for consumer to consume
        shouldProduce = false;
        notify();
    }

    // this method will be used by the consumer to consume
    synchronized public int getCount() throws InterruptedException {
        if (shouldProduce == true) {
            System.out.println("Consumer thread is gone to waiting queue");
            wait();
        }
        shouldProduce = true;
        notify();
        return count;
    }
}


class Producer extends Thread {

    // sharing the instance
    private Counter counter;

    public Producer(final Counter counter) {
        this.counter = counter;
        start();
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                counter.setCount(i);
                System.out.println("Producer produce " + i);
                Thread.sleep((int) Math.random() * 3000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

class Consumer extends Thread {
    private Counter counter;

    public Consumer(final Counter counter) {
        this.counter = counter;
        start();
    }

    public void run() {
        int total = 0;
        for (int i = 0; i < 10; i++) {

            try {
                System.out.println("Consumer :" + counter.getCount());
                total += counter.getCount();
                Thread.sleep((int) Math.random() * 3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ProdConsumerApp {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Producer producer = new Producer(counter);
        Consumer consumer = new Consumer(counter);
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }
}
