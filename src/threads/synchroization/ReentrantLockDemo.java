package threads.synchroization;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
//    Now synchronization is re-entrant.
//
//    What that means is that if a thread acquires an object's lock
//
//    and within the synchronized code, it calls a method
//
//    that's using the same object to synchronize some code.
//
//    The thread can keep executing
//
//    because it already has the object's lock.
//
//    In other words, a thread can acquire a lock it already owns.

// A ReentrantLock from java.util.concurrent.locks behaves like a synchronized block but with more flexibility
// (timeouts, interruptible waits, multiple conditions).
// “ReentrantLockDemo” means the thread that holds the lock can acquire it again without deadlocking itself.

//Reentrant – the same thread can acquire the lock multiple times (increments an internal counter).
//
//Useful in recursive calls or methods calling other locked method

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int    counter = 0;

    public void  add(){
        reentrantLock.lock(); // get the lock of the object now the countof the lock is 1
        try{
            counter++;
            System.out.println(Thread.currentThread().getName()+" entered add method in ReentrantLock and lock count is :: "+ reentrantLock.getHoldCount());
            Thread.sleep(1000);
         // call another method
         multiply();
        }catch(Exception e){

        }finally {
            reentrantLock.unlock(); // release the lock
            System.out.println(Thread.currentThread().getName() + " exited multiply method in ReentrantLock() and lock count is ::"+ reentrantLock.getHoldCount());
            System.out.println("counter value = " + counter);
        }
    }

    private void multiply() {
        reentrantLock.lock(); //
        try{
           counter *= 2;
            System.out.println(Thread.currentThread().getName()+" entered multiply method in ReentrantLock and lock count is ::"+reentrantLock.getHoldCount());
            Thread.sleep(1000);
        }catch(Exception e){

        }finally {
            reentrantLock.unlock(); // release the lock
            System.out.println(Thread.currentThread().getName() + " exited multiply method in ReentrantLock() and lock count is ::"+ reentrantLock.getHoldCount());
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(demo::add," first thread");
        Thread t2 = new Thread(demo::add," second thread");
        t1.start();
        t2.start();
    }
}
// What’s happening under the hood
//Thread-1 calls outer():
//
//Acquires the lock (hold count = 1)
//
//Prints “entered outer”
//
//Calls inner(), which calls lock.lock() again:
//
//Since Thread-1 already holds the lock, it’s allowed to re-acquire it (hold count = 2)
//
//Prints “entered inner,” increments counter, then unlocks (hold count = 1)
//
//Returns to outer(), finally unlocks (hold count = 0) and fully releases the lock.
//
//Meanwhile, if Thread-2 calls outer() while Thread-1 still holds the lock, it will block in lock.lock() until Thread-1’s final unlock() drops the hold count to zero.