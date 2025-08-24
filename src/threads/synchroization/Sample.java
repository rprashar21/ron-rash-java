package threads.synchroization;

public class Sample {


    public void run(){
        display();
        nondisplay();
    }

    public synchronized void display() {

        System.out.println("executing display method");

        System.out.println(Thread.currentThread().getName());

        System.out.println("finished display method");
    }

    public void nondisplay() {
        System.out.println("executing nondisplay method");
    }

    public static void main(String[] args) {
        Sample sample = new Sample();
        Thread thread1 = new Thread(sample::run);
        Thread thread2 = new Thread(sample::run);
        Thread thread3 = new Thread(sample::run);
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
