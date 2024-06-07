package threads.synchroization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizationExample {

    // synchronized is for methods and blocks but not for classes and variables
    // when multiple threads try to access same object then we shoud use synchronize
    // causes data inconsitency
    // internally it uses lock concept --
    // needs the lock of the object

    // 2 diff objects have 2 different locks
    // if a method is static synchronized then threads need class level lock
    // every class in java has a lock similar to

    // for static sync methods class level lock is required
    // for normal sync methods object level lock is required

   // synchronized(A.class) {A.class} --> class level lock   or object level synchronized(this)

    /*
    * Synchronized is keyword for methods and block nt for lcasses and variables
    * Resolves data inconsistency
    * dis-adavanateg of sync --> deadlocks ,perfromance
    *
    * What is race Condition ??
    * If multiple threads operate on same  object simultaneously then data inconsistency , this can be resolved using sync keyword
    *
    * Every class has an object level lock and is required by a thread to execute sync method/block
    * Every class has an class level lock and is required by a thread to execute static sync method/block
     *
    * */

    public static void main(String[] args) {

        Player player = new Player();
        ThreadCLas thread1 = new ThreadCLas("jack",player);
        ThreadCLas thread2 = new ThreadCLas("john",player);

//        thread1.start();
//        thread2.start();

        // If I use the ExceutorSrvice

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.submit(thread1);
        executorService.submit(thread2);

        System.out.println();

    }
}
class ThreadCLas extends Thread{
    Player player;
    String name;


    public ThreadCLas(final String name, Player player) {
        this.name = name;
        this.player=player;
    }

    public void run()
    {
        try {
//            player.display(name);
//            Player.staticDisplay(name);
            player.displaySynchronzedBlock(name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class Player {


    // now which ever thred  comes first it will act upon this
    private static final Object lock = new Object();
//    public synchronized void display(String name ) throws InterruptedException {
//        for(int i=0;i<2;i++)
//        {
//           Thread.sleep(1000);
//            System.out.println(name+" "+Thread.currentThread().getName()+"  non static block");
//        }
//
//    }
//
//    public synchronized static void staticDisplay(String name) throws InterruptedException {
//
//        for(int i=0;i<2;i++)
//        {
//            Thread.sleep(1000);
//            System.out.println(name+" "+Thread.currentThread().getName()+"  static block");
//        }
//    }

    // Non-static synchronized block
    public void displaySynchronzedBlock(String name) throws InterruptedException {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(name + " " + Thread.currentThread().getName() + "  non static block");
            }
        }
        // this is non blocking code
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " " + Thread.currentThread().getName() + " not locked");
        }
    }

}
