package threads;

public class ReentrantVsSeeamphore
{
    // A binary semaphore provides a signaling mechanism whereas reentrant lock is a locking mechanism
    // it allows a thread to entire a lock on a resource multiple times

    // Binary semaphores are non-reentrant by nature,
    // implying that the same thread can't re-acquire a critical section, else it will lead to a deadlock situation.
}
