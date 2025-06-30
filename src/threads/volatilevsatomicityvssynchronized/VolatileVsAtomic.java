package threads.volatilevsatomicityvssynchronized;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileVsAtomic {

    // volatile simply means that the variable update should be read from the main memory
    // u tell the jvm to read the value of the variable directly from the main memory which is the ram where shared object like heap are stored
    // when we wrte a program its loaded in the ram
    // if the variable is not volatile we cannot be sure when exactly it is written into the main memory
    // the volatile keyword is used to make a variable visible to all the threads in a multithreaded applications.
    // atomic gaurantees visibility and atomicity of operations performed on a variable

    // in summary, volatile is used for variables that are shared among multiple threads and need to be accessed without interference,
    // while atomic is used for operations that need to be performed as an atomic unit.

    // the values of thses variable are directly updated to the main memory  instead of the variable cache ,,
    // this makes sure the value is consistent
    // but decreases the performance.
}

class Counter {
    // one variable is volatile and the other not when 2 threads try to execute this it is possible that when u perform any operation
    // the cpu thread loads it to the cpu register and decides to keep with itself for some time without pushing the value in the main memory
    // which can casue the other thread to read stale value

    // so when we make a variable volatile the thread will always push the value to the main memory
    private volatile int count = 0;
    private int max = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class Counter2 {
    private AtomicInteger count = new AtomicInteger(0);
    // the count variable is an instance of the AtomicInteger and any operation on this will be done in atomicity

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





