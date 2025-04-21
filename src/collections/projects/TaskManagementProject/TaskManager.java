package collections.projects.TaskManagementProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TaskManager {

    // main class to add task ,, remove task

    // store all the tasks and maintain insertion roder
    private final List<Task> taskList = new ArrayList<>();

    // Auto Sort the tasks by priority and then due dat e
    private final PriorityQueue<Task> priorityQueue = new PriorityQueue<>();

    // Group The task based on project in a multithreaded application
    private final Map<String, List<Task>> projectMap = new ConcurrentHashMap<>();

    // add method

    public void addTask(Task task) {
        taskList.add(task);

        // auto sort
        priorityQueue.offer(task);

        // add the task to the project  data --{cleaning , analyzing} andother group can be s
        projectMap.computeIfAbsent(task.getProject(), k -> new ArrayList<>()).add(task);
        //
    }

    public void removeTask(int id) {
        // remove that task from the list
        taskList.stream().filter(task -> task.getTaskId() == id).findFirst().ifPresent(task -> {
            taskList.remove(task);
            // remove the task from the queue
            priorityQueue.remove(task);

            projectMap.computeIfAbsent(task.getProject(), k -> new ArrayList<>()).remove(task);
        });
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

   public Map<String, List<Task>> getMap() {
        return projectMap;
   }

   public PriorityQueue<Task> getPriorityQueue() {
        return priorityQueue;
   }

   public Task getNextTask() {
        return priorityQueue.peek();

   }

    public Task getLeastTask() {
        return priorityQueue.poll();

    }
}
