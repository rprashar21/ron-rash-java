package datastructures.queues;

import java.util.LinkedList;
import java.util.Queue;

public class Queue01 {

    /*
     *  Queues like stacks are abstract data types and can be implemented using linked list and arrays
     *  FIFO -- first in first out -- enqueue dequeue and peek()
     *  apps -- os and multithreading applications
     *  useful when resource is shared between several consumers
     *  Threads are stored in queues ,, used for cpu scheduling
     *   graph such as bfs
     *
     *
     *
     * */

    public static void main(String[] args) {

        // Queue is an interface -- LinkedListSample implement the Queue interface
        // Adding an element is 0(1)
        // it is a double linked list which stores ref to the first and last element of the linked list
        //In a queue we keep adding items to the end of the list and remove them from the begginging 321
        // We process elements in the first come first served manner
        Queue<Integer> integerQueue = new LinkedList<>();
        integerQueue.add(1);
        integerQueue.add(2);
        integerQueue.add(3);

        // with element method we can remove see the leemsnt without removing

        // remove method is dequeue method in
        while (!integerQueue.isEmpty()) {
            System.out.println(integerQueue.remove());
        }

        // Suppoes we have some tasks and we need to execute them as them come

        Queue<Task> taskQueue = new LinkedList<>();
        taskQueue.add(new Task("open laptop"));
        taskQueue.add(new Task("go  to youtube "));
        taskQueue.add(new Task("search for ds algo videos"));

        // execute these tasks in this first come first manner

        while (!taskQueue.isEmpty())
        {
            Task task = taskQueue.remove();
            task.execute();
        }
    }
}

class Task {

    private String name;

    public Task(final String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println("Executing task "+this.name);
    }
}
