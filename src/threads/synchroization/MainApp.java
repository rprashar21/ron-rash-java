package threads.synchroization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {

    public static void main(String[] args) {

            // 2 threads create
            Task task = new Task();
            Thread thread1 = new Thread(task);
            Thread thread2 = new Thread(task);

            thread1.start();
            thread2.start();
    }
}

class Task implements Runnable {


     static int count = 0;

    @Override
    public void run() {
        int calculate = calculate();
         result(calculate);
    }

    public synchronized int calculate() {
        System.out.println("count value is -> "+count);
        System.out.println("thread name is -> "+Thread.currentThread().getName());
        for (int i = 1; i <= 4; i++) {
            count =count+1;
        }
        System.out.println("finished for loop count is "+count);
        return count;
    }
    public  synchronized void  result (int calculate) {
        System.out.println("entered result thread name is -> "+Thread.currentThread().getName());
        for (int i = 0; i < 4; i++) {
            calculate =calculate+1;

        }
        System.out.println("finished for loop calculate count is "+calculate);
    }
}