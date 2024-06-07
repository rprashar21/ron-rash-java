package threads.producerconsumer;

public class App {
    public static void main(String[] args) {

        Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try{
                processor.producer();
            }
            catch (InterruptedException e)
            {
                e.getStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try{
                processor.consumer();
            }
            catch (InterruptedException e)
            {
                e.getStackTrace();
            }
        });

        t1.start();
        t2.start();

        //
    }
}
