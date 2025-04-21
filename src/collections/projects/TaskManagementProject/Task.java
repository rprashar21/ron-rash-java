package collections.projects.TaskManagementProject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Task implements Comparable<Task> {

    private int taskId;
    private String TaskName;
    private Priority priority;
    private LocalDateTime dueDate;
    private String project;


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(final int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(final String taskName) {
        TaskName = taskName;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(final LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(final String project) {
        this.project = project;
    }

    @Override
    public int compareTo(final Task otherTask) {
        // first compare with enum ranks ,,  high task comes up first ,, if tasks are same duedate comarision will be done
        int priorityCompare = Integer.compare(this.priority.getRank(), otherTask.priority.getRank());
        if (priorityCompare != 0) {
            return priorityCompare;
        }

        // If priorities are the same, compare by due date
        return this.dueDate.compareTo(otherTask.dueDate);
    }

    public Task(final int taskId, final String taskName, final Priority priority, final LocalDateTime dueDate, final String project) {
        this.taskId = taskId;
        TaskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
               "taskId=" + taskId +
               ", TaskName='" + TaskName + '\'' +
               ", priority=" + priority +
               ", dueDate=" + dueDate +
               ", project='" + project + '\'' +
               '}';
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(ThreadLocalRandom.current().nextInt(), "groceries", Priority.LOW, LocalDateTime.now(), null));
        tasks.add(new Task(2, "play football", Priority.MEDIUM, LocalDateTime.now().plusHours(2), null));
        tasks.add(new Task(4, "Take bibs ", Priority.MEDIUM, LocalDateTime.now(), null));
        tasks.add(new Task(1, "study", Priority.HIGH, LocalDateTime.now(), null));
        tasks.add(new Task(1, "study", Priority.HIGH, LocalDateTime.now(), null));

        // list will maintain the insertion order
        System.out.println(tasks);
        // After sorting

        // we will sort the list based on the rank
        Collections.sort(tasks);

        System.out.println(tasks);
    }


    // to make sure these object are comparable
    // do
}

enum Priority {
    LOW(3),
    MEDIUM(2),
    HIGH(1);
    private final int rank;

    Priority(final int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}

