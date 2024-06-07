package threads.synchroization;

public class Reentrant {
//    Now synchronization is re-entrant.
//
//    What that means is that if a thread acquires an object's lock
//
//    and within the synchronized code, it calls a method
//
//    that's using the same object to synchronize some code.
//
//    The thread can keep executing
//
//    because it already has the object's lock.
//
//    In other words, a thread can acquire a lock it already owns.
}
