package threads.concurrency;

public class ThreadsCreation1 {
    public static void main(String[] args) {

        // let change the main threads name
        Thread.currentThread().setName("Main Thread");
        Thread thread = new Thread(() -> System.out.println("Inside run method" + Thread.currentThread().getName()));

        thread.setName("daemon thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Thread name"+thread.getName());

        thread.start();

        for(int i=0;i<5;i++)
        {
            if(i%2==0)
            {
                System.out.println("Outside run method"+Thread.currentThread().getName());
            }
        }

        try {
            // blocking operation for the thread
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
