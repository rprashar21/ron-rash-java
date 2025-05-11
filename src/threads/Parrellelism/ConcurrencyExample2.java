package threads.Parrellelism;



// concurrecny means the ability of a program to deal with nulitple things by letting each other overlap for some period
// It doesn’t necessarily mean truly doing two operations at the exact same instant (that’s parallelism)
// In code, threads (or tasks) take turns running on a CPU core.
// The operating system or runtime pauses one thread, switches to another,
// then perhaps switches back—making it look like they’re happening together.


///  Coordinated access. When two threads need the same tool (e.g., a lock protecting a shared data structure),
///  they must coordinate so they don’t step on each other’s toes—just like two cooks sharing the same chopping board.

public class ConcurrencyExample2 {
    public static void main(String[] args) {
        // Still concurrent, as both threads are active and compete for the lock.
        // However, performTask runs sequentially due to the shared lock.
       ConcurrencyExample2 example = new ConcurrencyExample2();
        Runnable ioTask = () -> {
            String name = Thread.currentThread().getName();
            // if we create here bot
          //  ConcurrencyExample2 example = new ConcurrencyExample2();
            example.performTask(name);
        };

        Thread t1 = new Thread(ioTask, "Thread 1");
        Thread t2 = new Thread(ioTask, "Thread 2");
        t1.start();
        t2.start();
    }

    private synchronized void performTask(String name)
    {
        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " starting I/O operation " + i);
            try {
                // simulate blocking I/O
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(name + " finished I/O operation " + i);
        }
    }
}

// Each thread performs 3 i/o operations meaning hiiting a url and downloading a file
// While one thread is sleeping, the other can run its next step. They overlap their waits, even on a single-core machine

// Handling multiple file downloads or HTTP requests—each thread spends
// much of its time waiting for data, so you spin up several to keep the CPU busy.


// Where Does the Runnable Task Sit in JVM Memory?

// main thread has local vars pointing to the three heap objects. t1, t2
//
//Both Thread 1 and Thread 2 objects hold a reference to the same Runnable λ (so there’s just one lambda instance).
//
//The lambda itself “captures” the example reference internally (so it also points back to the single ConcurrencyExample2 object).

// When you call t1.start() and t2.start(), the JVM spins up two new threads, each with its own call stack.
//
//Both threads run the same ioTask.run(), which calls example.performTask(name).
//
//Because performTask is synchronized, both threads must acquire the same lock on the one example object.
// That makes their I/O loops run sequentially, even though the threads themselves exist and compete concurrently


// Summary
//Objects created at runtime:
//
//ConcurrencyExample2 instance
//
//Runnable λ instance
//
//Thread instance for “Thread 1”
//
//Thread instance for “Thread 2”