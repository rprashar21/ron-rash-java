package threads.coreThreads;

public class VirtualThreads {

    // the virtual threads are alos part of the jvm refer the diagram
    /// the vritual threads has a pool of platform threads to execute them,, they are not tied to os threads unlike platform threads
    // whihc have a 1:1 mappung with os threads niether do they have 1:1 mapping with platform threads

    // on eplatform thread can execute multiple virtiual threads

    // how to create var vthread = Thread.ofVirtual().name('v1')
    // vthread.start(()-{})

    // we can create a million vthreads but not platform threads as they will go out of memory

    // mounting and unmountibg -- vthreads assing themselves to the platform threads ,,when blocked they unmount themselves
    // virtual threads dont make the code faster it just utilizes teh resource effiecntly ,, better thruput not latency
    // they dont require os threads 
}
