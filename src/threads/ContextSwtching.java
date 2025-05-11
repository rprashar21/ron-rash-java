package threads;

public class ContextSwtching {
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
}
