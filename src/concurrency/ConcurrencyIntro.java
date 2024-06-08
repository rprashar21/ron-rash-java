package concurrency;

public class ConcurrencyIntro {

    /*
    Concurreny == multitasing
    Concurrenyc is achived by multi tasking between threads
    performance may vary depending upon multiple core
    * Concurrency means that on java task does not have t
    *  like one java process can start downloading and other can start drawing
    *  Java provides thread realted classes  so that we can create java concurrent classes
    *  Threads are on the mercy of the JVm and the OS

    When we turn on our computer especial program called the operating system is
    loaded from the disc into the memory.

   The operating system takes over and provides an abstraction for us.
   operating system takes the program from the disc and creates an instance of that application in the memory.
   That instance is called a process, and it's also sometimes called a context of an application.

   Each process/application which runs on the os is completely isolated and has  metadata like process id ects
   The heap contains the data app needs
   it contains atleast one main thread- which has 2 main things stack and instruction pointer

   In a multithreaded application all threads come with its own stack and own instruction pointer
   and rest of the components are shared

   Stack local variablea are stored,function calls
   instruction pointer --> Address of the next instruction

   What are context Switching Process and Threads
   Context Switching -- Switching between processes
   eg Process1 -- has 2 threads  thread1 thread2      Process 2 == has 3 threads
   Stop thread 1
   schedule thread 1 out
   schedule thread 2 in
   start thread

   context switching is not cheap, price we pay for multitasing

   Threads consume cpu resources
   when we switch to a diff thread -- store data of one thread and restore data for another thread

   Threads consume less than esources

   Context swithcing is cheap when we switch between threads from same process and to diff process

multithreading allows us to use concurrency and parallelism, which brings us more responsiveness and high performance.

How does the Operating System decide what thread to schedule?
The algorithm for scheduling threads is OS specific, and is fairly complex, but the general goal is exactly that
It maintains a dynamic propertys for each thread to give priority to interactive threads and avoid starvation

Mutiple threads share in same process share -- heap code process open files and metadata

multithreading allows us to use concurrency and parallelism,
which brings us more responsiveness and high performance.

    * */
}
