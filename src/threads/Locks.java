package threads;

import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    /*
     * java.utl.concurrent.locks
     *  Lock Interface --> lock object is similar to implict lock to execute synchronize method
     *  ReentrantLOck --> direct child class of object immplements Lock Interface
     *
     * */

    public static void main(String[] args) {
        // main Thread
        Display d = new Display();
        MyThread1 thread1 = new MyThread1(d);
        MyThread1 thread2 = new MyThread1(d);
        thread1.setName("Rohit Thread ");
        thread2.setName("Swati Thread ");
        thread1.start();
        thread2.start();
    }

}

class Display {

      ReentrantLock reentrantLock = new ReentrantLock();


    public void wish() {
        reentrantLock.lock(); // gets the lock of the object
        try {

            for (int i = 0; i < 10; i++) {
                System.out.println("Inside Display Method " + Thread.currentThread().getName() + " " + i);
                Thread.sleep(100);
            }

        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
          reentrantLock.unlock(); // release the lock of the object
    }
}

class MyThread1 extends Thread {

    private Display display;

    public MyThread1(Display display) {
        this.display = display;
    }

    public void run() {

        this.display.wish();
    }
}

