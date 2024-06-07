package threads;

public class ConcurrentExecutionExample {

    public static void main(String[] args) {
        // Create two threads
        Thread thread1 = new Thread(new CountingTask("Thread 1"));
        Thread thread2 = new Thread(new CountingTask("Thread 2"));

        // Start the threads
        thread1.start();
        thread2.start();
    }

    // A simple task that counts from 1 to 5
    static class CountingTask implements Runnable {
        private final String threadName;

        public CountingTask(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + ": Count " + i);
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

