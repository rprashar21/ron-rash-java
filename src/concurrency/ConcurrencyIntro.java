package concurrency;

public class ConcurrencyIntro {

    /*
    Concurrency is the ability of your program to deal (not doing) with many things at once and is achieved through multithreading.
    Do not confuse concurrency with parallelism which is about doing many things at once.

    Context switching is the technique where CPU time is shared across all running processes and is key for multitasking.


    Concurrenyc is achived by multi tasking between threads
    performance may vary depending upon multiple core

    *  like one java process can start downloading and other can start drawing
    *  Java provides thread realted classes  so that we can create java concurrent classes
    *  Threads are on the mercy of the JVm and the OS

Imagine you launch Process A and Process B. Each process starts with one “main” thread, and then spawns two worker threads:

    Process A:
  ├─ Thread A1   A1 and A2 will share the memory code segment and the heap memory
  └─ Thread A2

 Each thread will have its own stack register and counter
Process B:
  ├─ Thread B1
  └─ Thread B2

No thread in A can see or corrupt B’s data, because processes isolate their address spaces.

Since we have a single core --the os scheduler uses preemptive scheduler to give every runnable thread (across all processes)
a small time slice (quantum), then quickly context-switches to another thread

Steps
The scheduler keeps a queue (or priority heap) of all runnable threads: A1, A2, B1, B2.
it picks up the oldest waiting thread More advanced kernels use priorities, CFS (Completely Fair Scheduling), real-time classes

Context switch
Save the current CPU registers, program counter, stack pointer of the outgoing thread. then load the saved register/counter/stack for the
incoming thread now cpu is executing diff piece of code

it will execute this threa d for some time ,, if the thread has sleep or blocks any call a higher priority threads is given cpu time
The now‐runnable thread goes back into the queue; the scheduler picks the next one.

Concurrency is a broader concept—any approach that allows you to work on several things at once (threads, async/reactive I/O, actors,
 coroutines, etc.)

 Multithreading is a specific technique to achieve ,, one way to achieve concurreny

 Concurrency is an architectural goal (“I want smooth responsiveness, lots of tasks in flight, scalable handling of many requests”).
Multithreading is one mechanism to implement it.


when to use what


    * */
}
