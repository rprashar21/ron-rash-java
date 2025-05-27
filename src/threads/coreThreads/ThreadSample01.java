package threads.coreThreads;

public class ThreadSample01 {

   // https://www.educative.io/blog/multithreading-and-concurrency-fundamentals#why
    // what is a thread in java ??
    // thread is a single flow of execution  withing a program

    // multitasking -- the computers ability to performs serveral tasks or programs like listenig to music while coding
    // ie. more that one tasks are done concurrently ie nultiple process

    // multithreading is witjin a single process or a program -- execution of multiple threads

    // process an instance of a a program is called process

    //Internals of a thread
    // suppose u write a  java program give belown the jvm createas 2 threads along with the main thread
    // each thread has its own stack,pc and register -- this is actuall muti-threading
    // the jvm asks the os to create 2 kernel threads , the os creates 2 thread control blocks {sy a tiny folder for each thread}
    // the thread has its own stack pc and registers pc is like current task pointer --- imagine a sticky pinter saying which line if code to execute next
    // so that when the thread takes a break the os know where to start when resuming work
    // register values -- they are like storage in the cpu os copies all teh work done by the thread and keep it heres so the work does not get lost
    // stack is like a pointer or bookmark s simply a bookmark saying “this is where we stopped writing.”

    // STACK --> Memory region where methods are called ,argumnets are passed and local variables are stored
    // All the variables belong tho the thread executing on the stack

    // heap all objects threads ,static variable , object collection , members of classes ,
    // obhject remain on heap as long as they are referenced ,same for members,, static variables stay forerver
    // reference varailes for the members of the class are in heap

    /*
    *         void main (){
    *          int x=1
    *         int y=2
    *      int result =(x+y).    all of these reside on the stack
    *
    *             }
    * */

    //So whenever the OS switches away from one thread to run another, it:
    // Zips up the thread’s current PC note, register notes, and stack-bookmark into its TCB folder.
    //
    //Picks the next thread’s TCB folder, unzips it (restoring its PC, registers, and stack position), and hands the CPU over to that thread.

    //That folder is all the OS needs to pause and resume each thread exactly where it
    // left off—no confusion, no overlap, just neat, isolated snapshots of each thread’s little workspace


    // when we write a program the jvm creates a thread object whose task is defined by the main method ,,
    // the thread executes the statements of
    // the program until the method is over and the thread dies



    // 2 ways t create our own thread -- java provides a thread class
    // extending the thread class and implementing a runnable interface
    // in both cases run() method should be implemendted


   // Rather than dealing directly with Thread and low-level synchronization, Java provides the Executor framework
   // ExecutorService, Future, Callable, CompletableFuture
    //Offer more flexible task submission, result handling, and lifecycle management

//    When & Why to Use Threads
//    I/O-bound tasks: reading/writing files or network where you can overlap wait times.
//
//    CPU-bound tasks: parallel computation to utilize multi-core processors.
//
//    GUI responsiveness: offload heavy work from the Event Dispatch Thread (EDT) in Swing/JavaFX.
//
//    Server applications: handle multiple client connections in parallel.

    //The Executor framework is the modern, preferred way to manage thread pools and asynchronous tasks.
}
