package threads.Parrellelism;

public class ConcurrencyExample2 {
    public static void main(String[] args) {
        Runnable ioTask = () -> {
            String name = Thread.currentThread().getName();
            for (int i = 1; i <= 3; i++) {
                System.out.println(name + " starting I/O operation " + i);
                try {
                    // simulate blocking I/O
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(name + " finished I/O operation " + i);
            }
        };

        Thread t1 = new Thread(ioTask, "Worker-1");
        Thread t2 = new Thread(ioTask, "Worker-2");
        t1.start();
        t2.start();
    }
}

