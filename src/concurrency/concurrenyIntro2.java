package concurrency;

public class concurrenyIntro2 {
    // When to Use Which
    //Use the term “concurrency” when you’re talking about your application design:
    // “We need to handle 1,000 simultaneous connections without blocking,” or “We want responsive UIs and background work.”
    //
    //Use multithreading when you’re describing the specific implementation:
    // “We spawn a thread‐pool of size N” or “We call new Thread(...)” or “We submit Runnables to an ExecutorService.”


//    Both are concurrent: they handle many clients “at once.”
//
//    Only the second is multithreaded

//Concurrency is your design goal: overlapping tasks to improve utilization and responsiveness.
//
//Multithreading is one (very common) way to get concurrency in Java, by running multiple threads in the same process.
//
//You can be concurrent without multithreading (e.g. async/NIO),
// and you can be multithreaded without high concurrency (e.g. two threads doing sequential work).
// Choose the right tool for your workload and environment.

}
