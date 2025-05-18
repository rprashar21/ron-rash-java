package threads.coreThreads.introthreads;

public class BasicThreads {
    public static void main(String[] args) {
        SimplePojoObject A = new SimplePojoObject("Alpha");
        SimplePojoObject B = new SimplePojoObject("Beta");
        A.begin();
        B.begin();
    }
}
// since this class implements runnable it can be executed bu a thread
class SimplePojoObject implements Runnable{

    private Thread thread;
    private String threadName;

    public SimplePojoObject(final String threadName ) {
        this.threadName = threadName;
    }

    public void run(){
        System.out.println("Starting " + threadName);
    }

    public void begin(){
        if(thread==null){
            thread = new Thread(this,threadName);
            thread.start();
        }
    }
}

// Explanation look at the image
/*
*  the heap will contains 2 objects ,each object holds a referenece to the a thread
*
* Thread Area which is also a heap - Holds the actual Thread objects ,,
* each thread has its own stack with frames for start() (inside SampleDemo) and ultimately run()

 * This code
 Creates three threads: main, Alpha, and Beta.
On a single-core CPU, only one thread runs at a time, with the OS switching between them (context switching).
* On a multi-core CPU (e.g., 4 cores), the OS may run main, Alpha, and Beta on separate cores,
*  but since main finishes quickly (after starting the threads), typically only Alpha and Beta compete for CPU time.
* */