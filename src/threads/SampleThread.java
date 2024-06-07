package threads;

public class SampleThread {

    // thread a is a flow of execution --
    // thread scheduler is part of jvm and when multiple threads are waiting it is responsible to provide allocations to these threads but we are not
    // sure which thread will get the priority
    //thread etending and implemet runnable interface
    // prevent thread execution --> yeild join and sleep

    // join method -- the main thread will wait for other threads to finish execution

    public static void main(String[] args) {

        System.out.println("Main Thread starts "+Thread.currentThread().getName());

        // child thread creation
        ChildThread childThread = new ChildThread();
        childThread.start(); // registers thread with thread scheduler ,, other work  and invokes run method

        // thread states -- new/borm --- after start --> ready/runnable--- t.s-->running ---- > dead

        // this object implements the thread
        Thread anotherChildThread = new Thread(new AnotherChild());
        anotherChildThread.start();

        // these line will be executed by the main thread it will wait for the threads to finish the execution
        try {
            childThread.join();
            anotherChildThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // after start we have 2 new threads
        for(int i=0;i<10;i++)
        {
            System.out.println(i+" Main Thread "+Thread.currentThread().getName());
        }
    }

}
class ChildThread extends Thread
{
    public void run(){
        for(int i=0;i<10;i++)
        {
            System.out.println(i+" Child Thread "+Thread.currentThread().getName());
        }
    }
}

class AnotherChild implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++)
        {
            System.out.println(i+" Another Thread "+Thread.currentThread().getName());
        }
    }
}

