package threads.volatilevsatomicityvssynchronized;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class Samplecodedifference {

    // first when to use sysnchronization
    // in pub sub -- we awant entire
    // Use when you need mutual exclusion around a group of operations or fields, or when you rely on wait()/notify().

    // Thread-safe cache with write-through eviction

    // Why synchronized?
    //Ensures both the map and the access-order deque are updated as a unit.
    //Prevents race conditions between checking size, eviction, and insertion.

    public static void main(String[] args) {
        // example for synchronization
        LruCache<String,String> cache= new LruCache<>(10);

        // example for volatile
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();

        // asking the main thread to stop the background worker thread after 1 seconds
        try{
            Thread.sleep(1000);
            worker.stop();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}

class LruCache<K, V> {
    private final int capacity;
    private final Map<K, V> map;
    private final Deque<K> accessOrder;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.accessOrder = new ArrayDeque<>();
    }

    public V get(K key) {
        synchronized (this) {
            V value = map.get(key);
            if (value != null) {
                // move key to end (most-recently-used)
                accessOrder.remove(key);
                accessOrder.addLast(key);
            }
            return value;
        }
    }

    public void put(K key, V value) {
        synchronized (this) {
            if (map.containsKey(key)) {
                accessOrder.remove(key);
            } else if (map.size() == capacity) {
                K eldest = accessOrder.removeFirst();
                map.remove(eldest);
            }
            map.put(key, value);
            accessOrder.addLast(key);
        }
    }
}

// example of valoatile key word
// scenario --> graceful shutdown for a backgroudn worker thread
// A thread polls for work until you ask it to stop. No complex state updates—just a boolean.
// why vloatile as soon as
// running = false in one thread is immediately visible in run()’s loop.
//
//No need for locking or coordination beyond that single flag.

class Worker implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {
       while (running) {
           // so some task
           System.out.println("im working"+ Thread.currentThread().getName());
       }
    }
    public void stop() {
        running = false;
        System.out.println("im stop");
    }

}

// now an example of atomicity
// when u need lock free atomicity on individual variables , typically counters  or simple state machines
// Scenario: High-throughput request counter in a web service
//Millions of requests per second, contend on a counter without a full lock

 class SessionIdGenerator {
    private final AtomicInteger counter = new AtomicInteger();

    public int nextId() {
        // getAndIncrement() is atomic, no locks
        return counter.getAndIncrement();
    }
}
// or
class RequestTracker {
    private final LongAdder requestCount = new LongAdder();

    public void onRequestReceived() {
        requestCount.increment();  // very cheap, low contention
    }

    public long getTotalRequests() {
        return requestCount.sum();
    }
}
// Why atomic
// Provides atomic read-modify-write (e.g. incrementAndGet()) without full monitor overhead.
//
//LongAdder in particular scales better under heavy contention by stripin