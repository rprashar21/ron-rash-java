package threads.synchroization;

public class InterThreadCommunication {

    // every object in java has wait,notify,notifyAll methods and these should be called inside a sync method/block
    // thread calls wait() method on an object and then releases the lock of that object and goes to waiting state
    // thread calls notify()/notifyAll  method on an object and does not  releases the lock of that object immediately
    // thread only releases the lock in these three methods wait,notify and notifyAll

    // wait and notify is based on producer and consumer methods as well
    // this only occurs in the synchronized block

    /*
    * The best example for this is Producer Vs Consumer --> both are accessing the queue object
    *  if (ques is empty )--> q.wait --> wiat until you get a notification from producer t
    * */


    public static void main(String[] args) throws InterruptedException {

        System.out.println("MainThread starts execution "+Thread.currentThread().getName());

        //creating a childThread
        SomeObject someObject = new SomeObject();
        Thread thread = new Thread(someObject);
        thread.start(); // at this point there are 2 threads --> child thread and main thread

        // now i want the total value so i want the main thread to pause
        // waitt on current thread

//        Wait: releases the lock, parks the thread.
//
//        Notify: signals one waiter, but lock is held until the notifier exits its synchronized block.
//
//       Wake-up: the waiting thread only continues once it both receives the notify and re-acquires the lock.

        synchronized (someObject){ // main thread gets the lockk of the object SomeObject
            System.out.println("MainThread enters ,, release the child object lock and goes to waiting state "+Thread.currentThread().getName());
            someObject.wait();// main thread goes to waiting ,, once recives notification from child thread will start execution

            System.out.println("Total value is ::" + someObject.total);
        }


    }
}

class SomeObject implements  Runnable{

   int total=0;
    public void run (){

        // we can only uses wait notify inside synchronizeb methods or blocks

        synchronized (this){ // this is a sync block
            //
            System.out.println("child thread gets the object lock release by main thread and starts executing ");

            for(int i=0;i<=5;i++)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                total=total+i;
            }
            System.out.println("child thread finishes  execution ");
            System.out.println("child thread sends  notification ");
            this.notify(); // releases lock of this object but may not immediately
        }
    }
}