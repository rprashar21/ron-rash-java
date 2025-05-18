package threads.coreThreads;

public class ThreadSample {

    // what is a thread in java ??
    // thread is a single flow of execution  withing a program

    // multitasking -- the computers ability to performs serveral tasks or programs like listenig to music while coding
    // ie. more that one tasks are done concurrently ie nultiple process

    // multithreading is witjin a single process or a program -- execution of multiple threads

    // process an instance of a a program is called process

    // when we write a program the jvm creates a thread object whose task is defined by the main method ,, the thread executes the statements of
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
