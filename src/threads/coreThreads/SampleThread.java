package threads.coreThreads;

public class SampleThread {

    // thread a is a flow of execution -- exection path --so if a program has multiple execution paths then we can say its multithreading
    // thread scheduler is part of jvm and when multiple threads are waiting it is responsible
    // to provide allocations to these threads but we are not
    // sure which thread will get the priority
    //thread etending and implemet runnable interface
    // prevent thread execution --> yeild join and sleep

    // join method -- the main thread will wait for other threads to finish execution

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main Thread starts "+Thread.currentThread().getName());

        // child thread creation
        ChildThread t1 = new ChildThread();
        t1.join();
        t1.start(); // registers thread with thread scheduler ,, other work  and invokes run method

        // thread states -- new/borm --- after start --> ready/runnable--- t.s-->running ---- > dead

        // implement runnable
        AnotherChild t2 = new AnotherChild("t2 thread");
        AnotherChild t3 = new AnotherChild("t3 thread");
        AnotherChild t4 = new AnotherChild("t4 thread");



        // after start we have 2 new threads
        for(int i=0;i<10;i++)
        {
            System.out.println(i+" Main Thread "+Thread.currentThread().getName());
        }
    }

}
class ChildThread extends Thread
{
    @Override
    public void run(){
        for(int i=0;i<2;i++)
        {
            System.out.println(i+" Child Thread "+Thread.currentThread().getName());
        }
    }
}

class AnotherChild implements Runnable{
    private final Thread childThread;

    public AnotherChild(String name) {
        // this is the current class object, name is
        childThread = new Thread(this, name);
        childThread.start();
    }


    @Override
    public void run() {
        for(int i=0;i<2;i++)
        {
            System.out.println(i+" Another Thread "+childThread.getName());
        }
    }
}

