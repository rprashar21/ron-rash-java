package threads.concurrency;

//In this exercise we are going to implement a  MultiExecutor .
//
//The client of this class will create a list of Runnable tasks and provide that list into MultiExecutor's constructor.
//
//When the client runs the . executeAll(),  the MultiExecutor,  will execute all the given tasks.
//
//To take full advantage of our multicore CPU, we would like the MultiExecutor to execute all the tasks in parallel,
// by passing each task to a different thread.

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    private final List<Runnable> tasks;

    public MultiExecutor(final List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll(List<Runnable> tasks)
    {
        List<Thread> threadList = new ArrayList<>(tasks.size());
        for(Runnable runnable:tasks)
        {
            Thread thread = new Thread(runnable);
            threadList.add(thread);
        }

        for(Thread thread :threadList)
        {
            thread.start();
        }
    }

    public static void main(String[] args) {
        List<Runnable> tasks =new ArrayList<>();
        tasks.add(()->{
            System.out.println("Task cooking by thread "+Thread.currentThread().getName());
        });
        tasks.add(()->{
            System.out.println("Task washing by thread "+Thread.currentThread().getName());
        });
        tasks.add(()->{
            System.out.println("Task dancing by thread "+Thread.currentThread().getName());
        });
        tasks.add(()->{
            System.out.println("Task heading by thread "+Thread.currentThread().getName());
        });
        MultiExecutor client = new MultiExecutor(tasks);
        client.executeAll(tasks);
    }
}

