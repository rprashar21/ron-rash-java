package threads.synchroization;

public class MainApp {

    public static void main(String[] args) {

        // 2 threads create
        Task task = new Task();
        Thread t1 = new Thread(task::calculate);
        Thread thread2 = new Thread(task::calculate);

        t1.start();
        thread2.start();
    }
}

class Task {


    static int count = 0;

    public synchronized int calculate() {
        System.out.println("count value is -> " + count);
        System.out.println("thread name is -> " + Thread.currentThread().getName());
        for (int i = 1; i <= 4; i++) {
            count = count + 1;
        }
        System.out.println("finished for loop count is " + count);
        result(count);
        return count;
    }

    public synchronized void result(int calculate) {
        System.out.println("entered result thread name is -> " + Thread.currentThread().getName());
        for (int i = 0; i < 4; i++) {
            calculate = calculate + 1;

        }
        System.out.println("finished for loop calculate count is " + calculate);
    }
}