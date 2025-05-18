package threads.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTypes {
    public static void main(String[] args) {
        // 1. Fixed thread pool: fixed number of threads; tasks wait in an unbounded linked queue
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        // 2. Cached thread pool: creates new threads as needed; reuses idle threads; terminates threads idle for 60 seconds
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 3. Single-thread executor: one thread; tasks executed sequentially; tasks wait in an unbounded linked queue
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // 4. Scheduled thread pool: fixed number of threads; supports delayed and periodic tasks
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);

        // Define tasks
        Runnable task = () -> System.out.println("Breaking news");
        Runnable task1 = () -> System.out.println("Virat Kohli retired");
        Runnable task2 = () -> System.out.println("India at war with Pakistan");

        // Schedule a one-time task after 10 seconds
        scheduledExecutor.schedule(task, 10, TimeUnit.SECONDS);

        // Schedule a task to run first after 15 seconds, then every 10 seconds
        scheduledExecutor.scheduleAtFixedRate(task1, 15, 10, TimeUnit.SECONDS);

        // Schedule a task to run first after 15 seconds, then 10 seconds after the previous execution completes
        scheduledExecutor.scheduleWithFixedDelay(task2, 15, 10, TimeUnit.SECONDS);

        // (Optional) Shutdown executors when no longer needed
        // fixedThreadPool.shutdown();
        // cachedThreadPool.shutdown();
        // singleThreadExecutor.shutdown();
        // scheduledExecutor.shutdown();

        // What are corepoolsize  max pool size and KeepAlive time
        // no of thread will be equal to the core --ie corepoolsize --
        // KeepAlive time -- idle time for threads to be alive
        // max pool size -- kitna threads can be created for eg in cached and scheduled it can keep rising to Integer.Max_value

        //                 fixed        cached              scheduled         single
        // corepoolSize   const arg      0                  constr arg           1
        // maxpoolSize    same as core  Integer.MAX_VALUE   Integer.MAX_VALUE    1
        // KeepAliveTime   0             60                 60                    0

        // 0 means they live till the executor service is shutdown

        // let talk about the queues inseide each executor where the tasks are kept

        /*   Pool                       QueueType               Why
         *  fixedThreadPool            LinkedBlockingQueue      threads are limited , tasks can be unlimited -- so unbounded queue to store all tsks
         *  singleThreadPoolExecutor                            since the queue can nver be full , new threads are not created
         *
         *   CachedThreadPool          synchronousQueue         threads are unbounded(meaning no limit on the no of threads)-- no need to store tasks
         *                                                      Synchronous queue is queue with a sinle slot ,, when a task comes it is assigned a new thread
         *                                                      Idle threads are killed
         * ScheduledThreadPool         delayedWorkQueue        Queue that deals with times and delays
         *
         * custom pool               ArrayBlockingQueue       Bounded queue to store tasks ,, if queue gets full new thread is created unless count is lees than maxPoolSize
         *
         *  in ArrayBlockingQueue which is custom Pool when the blockingqueue is full ,, thread count is reached maxpoolsize the task is rejected
         *  there are policy to reject a task
         *
         *   Policy              what it means
         *  AbortPolicy          submiting new task throws runtime exceotion RejectedExecutionException
         *  DiscardPolicy        new tasks are silently discarded
         * DuscardOldestPolicy   oldest task is dropped to accomodate latest task
         * CallerRunsPolicy     basically the the task is rejected ,,the task is then handled by main thread ,, in that way main thread becomes slow and cannot execute task in the same pace
         * */

        // custom pool
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                100,
                120, TimeUnit.SECONDS, new ArrayBlockingQueue<>(300));

        Runnable runnable = () -> {
            System.out.println("Some task");
        };
        try {
            threadPoolExecutor.execute(runnable);
        } catch (RejectedExecutionException e) {
            System.err.println("RejectedExecutionException" + e.getMessage());
        }

    }

    // some lifecycle methods of the executor
    // service.shutdown()  initiates a shutdown wait for task to be completd

    // service.isShutdown() true -- is shutdown is initiated

    //service.isTerminated() -- retruns true if all task are completed
    // service.waitTermination(10,TimeUnit.SECONDS) // blocks until all tasks are completd
    // List<Runnable> runnableList = service.shutDownNow() -- will return all the runnables which are queued

    // Callable Interface return a vlaue --
    // futur is a placeholder for the value that is goin to come some time in the future
}
