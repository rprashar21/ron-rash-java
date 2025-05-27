package threads.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {


    /*   Limtations of  of a thread ,, create a thread, start the thread , join the thread, threads are expensive , they have their own
         runtime stack ,memory, registers
     *    Creating threads not good for every job
     *     Thread of pools are created and are readily avaliable
        number of threads in a machine = no of cores in that machine

        ADVANTAGES---> is that we do not have to manually create or start or join threads
        there is no latency when we recieve a request , we can fine tune the thread pool whihc helps us to control the thruput of the application
        Helps in achievng concurreny in our application, the systen  will not go out of memory because the threads are not created without any limits
        we can have enought threads to keep all our porcessors buys ,, the application will degrade gracefull if the system is under load
        Excecutor service is java is a Asynchronous Task execution engine
        Provides a way toa asynchronoulsy execute tasks and provide results in  a much simpleer way
        Executor service has WorkQueue and CompletionQueue
        when a task is submitted it is palced ib the work quueand when it is completed it is placed in the Completion Queue
        ExecutorSevice Must be shutdown or else it will keep running
     *    Runnable has run method  and does not return anything
     *   Callable has call method ,, return type is object and We can hold that using future
     *
     *   ExecutorService is good as we dont have to create and wait on the threads
     *   Problem with executorService is that it still blocks -->> i,e it has to wait
     *   When we  get from callable we can use get(1.TimeUnit) --> but if this time lapses we will not  get the result
     *   Secondly we don not have a proper way to combine the furture
     *
     *   Fork/Join -- is an extension of executorService  is designed to achieve  data parallelism --works recursively
     *   ExecutorService --  is designed to achieve  task based  parallelism
     *
     * */

    // It’s concurrent by design
    //You’ve created an ExecutorService and you’re using multiple threads.
    // That allows your tasks to be interleaved or actually run at the same time if you let them—but that potential alone is “threads.concurrency.”

    // It’s not truly parallel as written
    //Because of the immediate future.get(), you never let two tasks actually overlap.
    // You wait for each to finish before starting the next. That serializes them, so there’s no real parallel execution.

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final List<NewTask> newTaskList = Arrays.asList(new NewTask("payment", 10),
                new NewTask("shopping", 5),
                new NewTask("registartion", 3)
        );

        // No of cores
        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads: " + numThreads);
        ExecutorService service = Executors.newFixedThreadPool(numThreads); // the exec

        newTaskList.forEach(newTask ->
                {
                    //submit is a non blocking call ,,   hands your task off to the thread-pool’s work queue (and one of the pool’s threads will pick it up when it can).
                    final Future future = service.submit(newTask); // this is still a blocking call ??
                    try {
                   // future.get() is blocking: your main thread sits and waits until that particular task has finished and a result is available.
                        System.out.println("task number is " + newTask.num + " returns value : " + future.get());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }

                }

        );
        // shut the executor service

        Thread.sleep(5000);

        System.out.println("Now lets execute the following tasks parallely");

        // to achieve pure concurrecny or parllelism
        // In your example, your NewTask’s call() method computes and returns an int
        List<Future<Integer>> futures = new ArrayList<>();
       for(NewTask newTask : newTaskList) {

           futures.add(service.submit(newTask));
       }

       // Then wait for all of them

        for (int i = 0; i < futures.size(); i++) {
            System.out.println("Task " + i + " result: " + futures.get(i).get());
        }



        service.shutdown();
    }
}




class Task implements Runnable {

    String name;

    public Task(String name) {
        this.name = name;
    }

    public void run() {

        System.out.println(this.name + "..... Job Started by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Job Completed " + Thread.currentThread().getName());
    }
}

class NewTask implements Callable {

    String name;
    int num;

    public NewTask(final String name, final int num) {
        this.name = name;
        this.num = num;
    }

    public Object call() throws Exception {
        System.out.println(this.name + "..... Job Started by " + Thread.currentThread().getName());
        int sum = 0;
        for (int i = 0; i < num; i++) {

            sum = sum + i;
        }
        Thread.sleep(3000);
        return sum;
    }
}