package threads;

import java.util.stream.IntStream;

public class PreventThreadExecution {
// prevents thread execution
    // yield -- will wait the current thread to make oyher threads available
    // , sleep
    // join   --> which thread executes this will wait

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new JoinThreadExample());
        thread.start(); // executed by main thread
        thread.join(); // exectued by main thread -- main thread will have to wait until child thread finishes

        IntStream.rangeClosed(0,10)
                .forEach(i->{
                    System.out.println("Main THREAD"+Thread.currentThread().getName()+ " "+i);
                });
    }

}


class JoinThreadExample implements Runnable{

    public void run(){

        IntStream.rangeClosed(0,10)
                .forEach(i->{
                    System.out.println("Child THREAD"+Thread.currentThread().getName()+ " "+i);
                });
    }
}