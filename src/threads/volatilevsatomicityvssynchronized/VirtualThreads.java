package threads.volatilevsatomicityvssynchronized;

public class VirtualThreads {

    // An os thread aka platformthread is managed by the os kernel
    // when u do Thread t = new Thread(runnable)
    // t.start()
    // by default you’re creating a platform thread—one to one mapped to an OS thread


    // charateristics
    // Heavyweight: each thread reserves a sizable stack (typically 1 MB by default) and kernel resources.
    // Costly context-switches: switching between them involves OS overhead
    // f you do socket.read() or Thread.sleep(), the entire OS thread is parked, consuming its fixed stack and resources until unblocked

    // Virtual thread
    // is a lghtweight  thread-like abstraction that the JVM’s Loom scheduler multiplexes (“fibers”) over a small pool of carrier (OS) threads
    // . You still use new Thread(..., Thread.ofVirtual().start()) or Executors.newVirtualThreadPerTaskExecutor(), but under the hood these live in user space.

    // charateristics
    // light fast 1:n scheduling multile virtual threads mapped to os thread

//    Aspect	            Platform/OS Threads	                                Virtual Threads
//    Mapping	            1 Java thread → 1 OS thread	                        Many Java threads → few OS “carrier” threads
//    Memory footprint	    Large default stack (~1 MB+)	                    Tiny stack slices (tens of KB or less)
//    Creation cost	        Relatively slow/kernel calls	                    Very fast/in‐JVM allocation
//    Blocking semantics	Blocks OS thread	                               Parks just the virtual thread, not the carrier
//    Scalability	        Hundreds–thousands before pressure                 hundreds of thousands–millions easily

    public static void main(String[] args) {
        // Traditional platform thread
        Thread t1 = new Thread(() -> {
            // blocking call
//            String msg = blockingHttpClient.get(url);
//            System.out.println(msg);
        });
        t1.start();

// Virtual thread version
//        Thread v1 = Thread.ofVirtual().unstarted(() -> {
//            String msg = blockingHttpClient.get(url);  // doesn’t block a carrier thread
//            System.out.println(msg);
//        });
//        v1.start();

    }

}
