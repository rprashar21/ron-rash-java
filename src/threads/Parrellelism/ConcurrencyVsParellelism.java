package threads.Parrellelism;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConcurrencyVsParellelism {


    /*
     *  Concurrency vs Parallelism
     *
     *  Concurrency is a concept where 2 or more tasks run simultaneously by sharing the common object-- achieved by using threads
     *  Concurrency is more about how to control access to shared resoures
     *  Threads run in an interleaved/alternating fashion in a single/multi core
     *
     *  In parallelism --> runs only in a multi-core machine
     *  Here the tasks actually run in parallel.
     *  Decomposing the task into subtasks forking and then joining the results of the  tasks
     *  This is called FORK/JOIN
     * Parallelism is more about using resources to access the result faster
     *
     * *  ExecutorService is good as we dont have to create and wait on the threads
     *    Problem with executoService is that it still blocks -->> i,e it has to wait
     *   When we use get from callable we can use get(1.TimeUnit) --> but if this time lapses we will not  get the result
     *   Secondly we don not have a proper way to combine the furture
     *
     *    Fork/JOin -- is an extension of executorService  is designed to achieve  data parallelism --works recursively
     *   It uses the divide and conquer rule
     *   The fork/join has some worker threads in the worker queue polling for tasks in shared queue
     *    keep polling for the shared workd queue wher the task is submitted
     *   when the task is submitted it is take by one of the threadsa
     *   it will split the task into subtasks and join them after execution
     *   There is a concept of workstealing where other treads steal ork from the thread which overloaded with tasks
     *   tis the reason why we have a double ended queue
     *   ExecutorService --  is designed to achieve  task based  parallelism
     *
     *   Concurrency                      Parrellelism
     *   concept where 2 o more tasks     task literally run parallel
     *   run simultaneously
     *   It can be achived in a single     This can only be achievd in a multi core machine
     *    or multipe cores
     *
     * Concurrency is basically controlling
     * how we acces shared objects between
     * multiple threads
     *   using shared objects via threads       using more resources to access result faster
     *   to efficiently access shared resources
     * */

    public static void main(String[] args) {


        // example of parallelism
        // Make them Capital
        final List<String> stringList
                = Arrays.asList("rohit", "swati", "shabu", "ashivin");

        // this is not asynchronous this will totalyy depend upon the hardware
        final List<String> namesInUpperCase = stringList.
                parallelStream()  // fork/Join framework // now depedning upon the number of cores the task will be divided
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(namesInUpperCase);

    }
}
