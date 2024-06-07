package threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Locks2 {


    // mor about static lock
    public static void main(String[] args) {
        ChildThread2 t1 = new ChildThread2("First");
        ChildThread2 t2 = new ChildThread2("Second");
        // main thread
        System.out.println("exectued by the main thread :: "+Thread.currentThread().getName());
        t1.start();
        t2.start();
    }

}
class ChildThread2 extends Thread{


    public ChildThread2(final String name) {
        super(name);
    }

    static ReentrantLock reentrantLock = new ReentrantLock();

    private int total=0;

    public void run()
    {
       do{
           try {
               if(reentrantLock.tryLock(5000, TimeUnit.MILLISECONDS)){
                   System.out.println("first method  "+Thread.currentThread().getName());
                   try{
                       Thread.sleep(30000);
                       System.out.println(Thread.currentThread().getName()+" releases lock");
                   }
                   catch (InterruptedException e){
                       System.out.println(e.getMessage());
                   }
                   reentrantLock.unlock();
                   break;
               }
               else {
                   System.out.println(Thread.currentThread().getName()+" unable to get the lock will try again ");
                   System.out.println("Next thread is performing some action ");
                   for(int i=0;i<20;i++)
                   {
                       total = total+i;
                   }
                   System.out.println(Thread.currentThread().getName()+" cals value"+total);
               }
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       } while (true);
    }
}