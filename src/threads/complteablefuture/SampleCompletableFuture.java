package threads.complteablefuture;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

public class SampleCompletableFuture {

    // CompletableFuture in background usess the Fork/JOin Pool framework
    // Ayschronous -- non blocking call
    // call returns immediatley but response is sent when it is available
    // exception doest break ur app
    // it normally runs in  pool of threads
    // these aysnc compution interatct with each other thru messages in an event driven style

    //CompletableFuture -- has 3 methods Factory Completion Exceptions
    // supplyAysnc() -- initiates the aysnc process ,takes a supplier as input ,returns  CompletableFuture <T>
    // and theAccept() -- Thus is the completion stage
    // input consumer as input input and  returns CompletableFuture <void>

    /*
     *
     *   thenApply()-- completion stage method which takes in the completed aysnc result and acts upon that
     *   transforms one data from one form to  another
     *    input is a FunctionalInterface - takes an input and retuns an output
     * */

    public static void main(String[] args) {

        HelloWorldService helloWorldService = new HelloWorldService();
        CompletableFuture
                .supplyAsync(()->helloWorldService.helloWorld())  // takes in supplier and return a value
                .thenAccept(s -> System.out.println(s.toLowerCase()));// theAccept method has A consumer Interface whihc consumes or takes an inout and consumes that value

        System.out.println("This is executed by the main thread "+Thread.currentThread().getName());

        CompletableFuture
                .supplyAsync(()->helloWorldService.helloWorld()) // this is part is called a pipeline  and runs in a fork/join pool
                .thenApply(String::toUpperCase) // this basically a competion stage method which takes the result of the previous aysnchronous call
                // and then apply some thing on that data
               // .thenApply((result)->result.toUpperCase()) // functional Interface -- it will take the input and so someting with it
                .thenAccept((result)-> System.out.println(result));

        System.out.println("call keeps going "+Thread.currentThread().getName());
        CompletableFuture
                .supplyAsync(()->helloWorldService.helloWorld()) // this is part is called a pipeline  and runs in a fork/join pool
                .thenApply(String::toUpperCase) // this basically a competion stage method which takes the result of the previous aysnchronous call
                // and then apply some thing on that data
                // .thenApply((result)->result.toUpperCase()) // functional Interface -- it will take the input and so someting with it
                .thenAccept((result)-> System.out.println(result))
                .join() // this will make the main thread wait
        ;

        System.out.println("call has waited  "+Thread.currentThread().getName());
        try{
            Thread.sleep(2000);
            Calendar calendar = Calendar.getInstance();
        }catch (InterruptedException exception){

        }

        System.out.println(Thread.currentThread().getName()+" done");
    }
}


class HelloWorldService {

    public  String helloWorld()  {
        try{
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        }catch (InterruptedException exception){

        }
        return "hello world ";
    }
}