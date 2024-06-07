package threads.executors;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSample {

    // first look at the ThreadPool
    /*
    *  This is an extension of ExecutorService,
    *  THis is used to achive data parellelism where as Executor service is used to achiev task based parallelism
    *  Data uis divided ,, executor service main task is divided
    *  Data parallesim means the task is split into subtask
    *
    *  Executor servcice
    *
    *  Internals of a ForkJoin Pool
    *  This common ForkJoin Pool is used by the ParallelStreams and Completable Future
    *   it has shared queue where tasks are submitted
    *   it also has a deck double ended queue backed by one thread ,, no of threads depend upon the m,achine
    *   so when a task is submitted in the shared queue from their it is being continoulsy polled and placed in the deck
    *   by one thread and task is divided inside the deck and then it is looked by other threads and this is called work stealing where they
    *   steal the work of the thread and combine an  do together
    *
    *
    * */

    public static void main(String[] args) {
        System.out.println("no of cores "+Runtime.getRuntime().availableProcessors());
        System.out.println("no of cores "+ ForkJoinPool.getCommonPoolParallelism());
    }
}
