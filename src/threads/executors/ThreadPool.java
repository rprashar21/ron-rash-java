package threads.executors;

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
        Advantage is that we do not have to manually create or start or join threads
        Helps in achievng concurreny in our application
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

    public static void main(String[] args) {
//        final List<Task> tasks = Arrays.asList(new Task("Task1"),
//                new Task("Task2"),
//                new Task("Task3"),
//                new Task("Task4"),
//                new Task("Task5")
//        );
//
//
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        tasks.stream()
//                .forEachOrdered(task -> service.submit(task));

        final List<NewTask> newTaskList = Arrays.asList(new NewTask("payment", 10),
                new NewTask("shopping", 5),
                new NewTask("registartion", 3)
        );
        ExecutorService service = Executors.newFixedThreadPool(2); // the exec
        newTaskList.forEach(newTask ->
                {
                    final Future future = service.submit(newTask); // this is still a blocking call ??
                    try {
                        System.out.println("task number is " + newTask.num + " returns value : " + future.get());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }

                }

        );
        // shut the executor service
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

        return sum;
    }
}