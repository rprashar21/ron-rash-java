package threads.concurrency;

import java.util.Arrays;
import java.util.List;

public class MultiExecutor2 {


    public void executeAll(List<Task> tasks)
    {
        int i =0;
        for(Task task:tasks)
        {
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    public static void main(String[] args) {
        MultiExecutor2 multiExecutor2 = new MultiExecutor2();
        // create a list of tasks
        Task task1 = new Task("Waking up first ");
        Task task2 = new Task("Going to gym ");
        Task task3 = new Task("Start Working");
        List<Task> taskList = Arrays.asList(task1, task2, task3);
        multiExecutor2.executeAll(taskList);
    }
}


class Task implements Runnable{

    private String name;

    public Task(final String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Executing task -> "+name+" Thread is "+Thread.currentThread().getName());
    }
}
