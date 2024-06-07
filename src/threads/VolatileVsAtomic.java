package threads;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileVsAtomic {

    // the volatile keyword is used to make a variable visible to all the threads in a multithreaded applications.
    // atomic gaurantees visibility and atomicity of operations performed on a variable

    // in summary, volatile is used for variables that are shared among multiple threads and need to be accessed without interference,
    // while atomic is used for operations that need to be performed as an atomic unit.

    // the values of thses variable are directly updated to the main memory  instead of the variable cache ,, this makes sure the value is consistent
    // but decreases the performance.
}
 class Counter {
    private volatile int count = 0; // any change made to this var will be visible to all th threds

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class Counter2 {
    private AtomicInteger count = new AtomicInteger(0); // the count variable is an instance of the AtomicInteger and any operation on this will be done in atomicity

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
// In this example, the count variable is an instance of AtomicInteger,
// which provides atomic operations for integer values.
// The incrementAndGet() method atomically increments the value of count and returns the updated value.
// This ensures that the operation is executed as a single, indivisible unit,
// without any possibility of interference from other threads.





