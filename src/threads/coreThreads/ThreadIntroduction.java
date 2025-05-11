package threads.coreThreads;

public class ThreadIntroduction {
    // A process is a program executioon like msword ,aint browising
    // thread is als a process or a unit of execution within a process and they share memory and that is why we have to deal
    // with concurrent programming


    // In a microservices world we have to deal with blocaking calls -- apply asynchronous process ie. completeableFUture


    // CONCURRENCY VS PARALLELISM -- ONE CPU MULTI THREADING IS DIFFICULT
    // lifecycle of threads

    // new state -- every thread is in new state until the start method
    // active state -- 2 substates -- runnable --afte the start method is called ,, running when cpu is aasigned
    // blcked/waiting state -- when the thread is waiting for

    // the threads of the same process run in a shared memory space

    // this will be exectued by the main thread

    public static void main(String[] args) {
        Mythread t1 = new Mythread("child thread");
        t1.start();

            // this will make the main thread wait until child thread completes its executon
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }

    }


}

class Mythread extends Thread {

    // thread name
    public Mythread(String name) {
        super(name);
    }
    @Override
    public void run() {
        // calcualte factorial of a num
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

}