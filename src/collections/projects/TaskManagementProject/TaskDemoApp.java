package collections.projects.TaskManagementProject;

import static java.time.LocalDateTime.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class TaskDemoApp {

    // we will create a list of tasks display them and remove or add tasks from our schedule
    // maybe this can be  our way or displaying task

    // what have learned here ,, why doe we nned an array list
    // we do we need a quueu
    // why do we meed a map why they are the best data structures to use in this complex

    // what is the perfomance can this be ,ultithreaded can we use proper memory

    public static void main(String[] args) {

        // create a list of task
        List<Task> tasks = new ArrayList<Task>();

        // create a

        TaskManager manager = new TaskManager();
        // Create some tasks
        manager.addTask(new Task(1, "Write report", Priority.MEDIUM,
                now().plusDays(2), "Work"));
        manager.addTask(new Task(2, "Buy groceries", Priority.LOW,
                now().plusDays(1), "Personal"));
        manager.addTask(new Task(3, "Pay bills", Priority.HIGH,
                now().plusHours(5), "Personal"));
        manager.addTask(new Task(4, "Team meeting", Priority.HIGH,
                now().plusHours(3), "Work"));

        System.out.println("******** Printing all the tasks *******");
        List<Task> allTasks = manager.getAllTasks();
        System.out.println(allTasks); // all the tasks will be kept in the insertion order

        Map<String, List<Task>> map = manager.getMap();
        System.out.println(map); // tasks are kept or grouped based on project , we can quickly retrieve what task are personl )(1)

        PriorityQueue<Task> priorityQueue = manager.getPriorityQueue();
        System.out.println(priorityQueue); // taks are kep in the queue

        //  get next task
        Task nextTask = manager.getNextTask();
        System.out.println(nextTask);

        Task nextTask1 = manager.getLeastTask();
        System.out.println(nextTask1);
    }
}
