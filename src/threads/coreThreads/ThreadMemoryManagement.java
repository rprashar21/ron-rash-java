package threads.coreThreads;

public class ThreadMemoryManagement {

    // it will be laoded at class time 
    private static final Object object = new Object();
    // the fields id, Name, and thread are in that heap‐object.
    private int id;
    private String Name;
    private Thread thread;

    public ThreadMemoryManagement(final int id) {
        this.id = id;
    }

    public void start() {
        synchronized (object) {
            System.out.println("Starting thread " + id);
       //     A Thread object and a small Runnable lambda object are allocated on the heap.
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Starting thread " + id);
                }
            });
            thread.start();
        }
    }

//
    public static void main(String[] args) {
        // the reference varibale will be in the main stack --- object will be in the heap -- Its fields id, Name, and thread are in that heap‐object.
        ThreadMemoryManagement   threadMemoryManagement = new ThreadMemoryManagement(10);
        //     A Thread object and a small Runnable lambda object are allocated on the heap.
        // t1 will live on the main stack
        Thread t1 = new Thread(()->{
            System.out.println("Starting thread 1");
        });
        // this will spin a new thread which will gets its won stack
        t1.start();
    }
}
