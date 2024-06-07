package datastructures.stacks;

import java.util.Deque;
import java.util.Stack;

public class Stack01 {

    /*
     * Stacks are abstract data types and not data structures .
     * They can be implemented using linkedLists and arrays
     * LIFO -- last in first out --- methods push pop peek
     * Last elemnet is the head node --> have access to all the elements
     *  Java memory has stacks ,
     *  Stacks store functions calls called frames  and local variables -- fast access ram and no fragmentation
     *  Graph algos rely heavuliy on stacks DFS is implemented using stacks
     *  find Eulerian cycles in a G(V,E) graph
     *
     *  Stacks are useful for interpreting huge xml files using sax or stax --
     *  the shunting yard algoruthm is method used to parse mathematical expressions  using stacks
     *  DIjKStra algo
     *  Implementation of mathematocal operation like ((2+2)) - ((3-1)) == 2
     *   take 2 stacks operationstack and value stack ,, keeping aading values and pop and evaluate the espression
     *
     * # real world apps
     *  back button -->  recent visisted urls
     *  undo operation
     *
     *
     * */

    public static void main(String[] args) {


        // Stacks are implemented in java
        Stack<String> stringStack = new Stack<>();
        stringStack.push("rohit");
        stringStack.push("swati");
        stringStack.push("myraa");

       while(!stringStack.isEmpty())
       {
           System.out.println(stringStack.pop());
       }





        int swati = stringStack.search("swati");
        System.out.println(swati);

        // do the most important task first
        Stack<Task> taskStack = new Stack<>();
        taskStack.push(new Task(" study soemthing"));
        taskStack.push(new Task(" office  work"));
        taskStack.push(new Task("ohh shit wake up most important"));

        while (!taskStack.isEmpty()) {
            taskStack.pop().execute();
        }
    }
}

class Task {

    private String name;

    public Task(final String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println("Executing task " + this.name);
    }
}
