package threads;

public class BasicThreads {
    public static void main(String[] args) {
        SampleDemo A = new SampleDemo("A");
        SampleDemo B = new SampleDemo("A");
//        Thread t1 = new Thread(A);
//        Thread t2 = new Thread(A);
//        t1.start();
//        t2.start();
        A.start();
        B.start();
    }
}
class  SampleDemo implements Runnable{

    private Thread thread;
    private String threadName;

    public SampleDemo(final String threadName ) {
        this.threadName = threadName;
    }

    public void run(){
        while(true)
        {
            System.out.println(threadName);
        }
    }

    public void start(){
        if(thread==null){
            thread = new Thread(this,threadName);
            thread.start();
        }
    }
}