package threads.synchroization;

import java.util.concurrent.Semaphore;

public class ReentrantVsSeeamphore {
    // A binary semaphore provides a signaling mechanism whereas reentrant lock is a locking mechanism
    // it allows a thread to entire a lock on a resource multiple times

    // Binary semaphores are non-reentrant by nature,
    // implying that the same thread can't re-acquire a critical section, else it will lead to a deadlock situation.

//              | Lock Type        | Analogy                                                                                  |
//        | ---------------- | ---------------------------------------------------------------------------------------- |
    //        | ReentrantLock    | You enter your room, forget your keys, and re-enter — no issue because you're the owner  |
//        | Binary Semaphore | You enter a meeting room — and block even yourself if you try to re-enter before exiting |


    // | Use Case                                         | Use Binary Semaphore                 | Use ReentrantLock           |
    //| ------------------------------------------------ | ------------------------------------ | --------------------------- |
    //| Cross-thread signaling (e.g., Producer/Consumer) | ✅ Yes                                | ❌ No                        |
    //| Locking critical sections                        | ⚠️ Yes, but risky if re-entrant      | ✅ Yes (safe, flexible)      |
    //| Recursive function or nested locking             | ❌ Deadlock risk                      | ✅ Reentrant, handles safely |
    //| Thread-safe mutual exclusion                     | 🚫 Only if you can ensure no reentry | ✅ Ideal choice              |


    static Semaphore seamphore = new Semaphore(1); // Binary semaphore (1 permit)
    public static void main(String[] args) {


        Runnable task = () -> {
            try {
                enter();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };
        new Thread(task).start();
    }


    private static void enter() throws InterruptedException {

        System.out.println("Acquiring lock ");
        seamphore.acquire();
        System.out.println("entering a critical section");

        ///  now if we try to reaquire a lock again it will fail
        System.out.println("trying to reacquire lock");
        seamphore.acquire(); // enter a deadlock situatioo

        System.out.println("Re-entered (won't print)"); // this line will not be printing as it has entered a lock situation
        seamphore.release();
        seamphore.release();
    }

    // but if its a reeentrant lock it can qcauire multiple locks

}
