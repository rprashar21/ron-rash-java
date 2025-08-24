package threads.volatilevsatomicityvssynchronized;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample {

    // acountdown latch is a synchronization aid which allows one or more threads to wait until a set of operations being performed
    // in other thread completes

    // Real Life Analogy
    //A rocket launch waits for 3 services to report "ready": Fuel system, Navigation system, and Communication system. Only after all report ready, the rocket can launch.
    //This is exactly what CountDownLatch helps us simulate.

    // | Method                  | Description                          |
    //| ----------------------- | ------------------------------------ |
    //| `new CountDownLatch(n)` | Initializes the latch with count `n` |
    //| `countDown()`           | Decrements the count by 1            |
    //| `await()`               | Blocks until the count reaches 0     |

    public static void main(String[] args) throws InterruptedException {
        int numberofServices = 3;

        CountDownLatch countDownLatch = new CountDownLatch(numberofServices);

        Thread fuelSystem = new Thread(new Service("Fuel System", 2000, countDownLatch));
        Thread navigationSystem = new Thread(new Service("Navigation System", 4000, countDownLatch));
        Thread communicationSystem = new Thread(new Service("Communication System", 3000, countDownLatch));


        fuelSystem.start();
        navigationSystem.start();
        communicationSystem.start();

        System.out.println("🚀 Main thread waiting for services to be ready...");

        // Wait until all services have called countDown()
        countDownLatch.await();

        // the main thread will wait for all thre services to be ober

        System.out.println("✅ All systems ready. 🚀 Launching rocket!");
    }


    static class Service implements Runnable {

        private final String name;
        private final int timeToStart;
        private final CountDownLatch latch;

        Service(String name, int timeToStart, CountDownLatch latch) {
            this.name = name;
            this.timeToStart = timeToStart;
            this.latch = latch;
        }

        public void run() {
            try {
                System.out.println(name + " initializing...");
                Thread.sleep(timeToStart); // Simulate startup time
                System.out.println(name + " ready!");
                latch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

