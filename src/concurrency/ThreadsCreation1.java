package concurrency;

public class ThreadsCreation1 {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside run methid"+Thread.currentThread().getName());
            }
        });

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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
