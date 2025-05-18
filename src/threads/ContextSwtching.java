package threads;

public class ContextSwtching {

    // A process is a program executioon like msword ,aint browising
    // thread is als a process or a unit of execution within a process and they share memory and that is why we have to deal
    // with concurrent programming

    // what really happens under the hood
    // suppose u write a  java program give belown the jvm createas 2 threads along with the main thread
    // each thread has its own stack,pc and register -- this is actuall muti-threading
    // the jvm asks the os to create 2 kernel threads , the os creates 2 thread control blocks {sy a tiny folder for each thread}
    // the thread has its own stack pc and registers pc is like current task pointer --- imagine a sticky pinter saying which line if code to execute next
    // so that when the thread takes a break the os know where to start when resuming work
    // register values -- they are like storage in the cpu os copies all teh work done by the thread and keep it heres so the work does not get lost
    // stack is like a pointer or bookmark s simply a bookmark saying “this is where we stopped writing.”

    //So whenever the OS switches away from one thread to run another, it:
    // Zips up the thread’s current PC note, register notes, and stack-bookmark into its TCB folder.
    //
    //Picks the next thread’s TCB folder, unzips it (restoring its PC, registers, and stack position), and hands the CPU over to that thread.

    //That folder is all the OS needs to pause and resume each thread exactly where it
    // left off—no confusion, no overlap, just neat, isolated snapshots of each thread’s little workspace



    // Context switching is the process by which the OS pauses one thread and resumes another on a CPU core.

    // Steps in context switching:
    //Save the current thread’s state (e.g., PC register, stack pointer, CPU registers).
    //Load the next thread’s state.
    //Resume execution of the next thread.
    //Context switching incurs overhead (typically microseconds) due to saving/restoring state and cache invalidation


//    How It Works
//    When multiple threads are ready to run but there are fewer CPU cores, the OS switches between them.
//    Steps in context switching:
//    Save the current thread’s state (e.g., PC register, stack pointer, CPU registers).
//    Load the next thread’s state.
//    Resume execution of the next thread.
//    Context switching incurs overhead (typically microseconds) due to saving/restoring state and cache invalidation.

    static final Object obj = new Object();
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            // some non blocking call
            System.out.println(Thread.currentThread().getName());
            obj.hashCode();
        });
        Thread t2 = new Thread(() -> {
            // some another bon blocking call
            System.out.println(Thread.currentThread().getName());
            obj.toString();
        });

        t1.start(); // 2 threads create along with the main thread
        t2.start();
    }
}
