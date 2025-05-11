package threads.volatilevsatomicityvssynchronized;

public class VolatileSample {

    // A volatile variable in Java gives you two main guarantees
    // When one thread writes to a volatile field, that write is immediately made visible to all other threads.
    //
    //Read freshness: When another thread reads that same volatile field, it sees the most recent write.

    // volatile can only be applied to instance or static fields -- not inside methods becio

    private volatile int instanceCounter;    // ✅ instance field
    // this will only gaurantee the visbilty of the reference vairable
    public static volatile boolean isReady;  // ✅ static field

    // Cannot do:
    // volatile class Nested { … }          // ❌ illegal
    // volatile void someMethod() { … }     // ❌ illegal
    // volatile int localVar = 0;           // ❌ illegal

    public void bar() {
   //     Why not locals? Local variables live on the thread’s own stack and aren’t shared, so there’s no visibility problem to solve
 //       volatile int local = 0;        // ❌ compile-error: illegal start of expression


//        Use volatile
//
//        For simple flags or state markers (e.g. a shutdown signal).
//        When you need the latest value immediately and don’t need to coordinate compound updates.

        // synchronized keyword is used for mutula exclusion and multiple operations , u need to wait/notify and complex mutiple vairable consistency
        // why cannot volatile replace sysnchronized
        // 1. multiple threads can execute the same code ,, synchrozine main only one thread ata time
        // 2 . only individual read/writes are atomic not entire block
        // 3 . No built-in wait/notify
    }
}
