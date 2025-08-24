package threads.complteablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureIntro {

    //  // CompletableFuture is a java class that holds the future result of an aysnchronous computation
    //  with Completable future we can run tasks asynchronously,
    // avoid blocking -- we can combine/compose the future results of these async taks ,, we can achieve paralleism and concurreny
    // Handle success and failure explicitly

    // it has 3 methods
    // supplyAsync() -- initiate the async process -- perform tasks in background
    // thenApply() -- apply the future results
    // thenAccept -- completion stage

    //Aysnchronous --> Call returns immediately , but the response is sent when it is available,
   //Exception or error wont crash the system

//Asynchronous  computations normally run in a pool of threads that is the threads can go up and down

//Asynchronous  computations interact with each other thru messages in a event diven style.

    // when to use Completbale future
    // | ✅ Use CompletableFuture When...                         | ❌ Don’t Use When...                                                                          |
    //| ------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
    //| You want **async computation**                          | Task must be **synchronous**                                                                 |
    //| You want to **compose** multiple async steps            | You need **stream/batch processing**                                                         |
    //| You’re calling **external services** (API, DB, IO)      | Your task is **CPU-bound** and doesn't benefit from async                                    |
    //| You want to avoid **manual thread handling**            | You’re in a **highly multithreaded, low-latency system** (consider more advanced frameworks) |
    //| You want better **exception handling** than raw threads | You just need a one-off thread, not chaining                                                 |


    //| Method                  | Description                                                       |
    //| ----------------------- | ----------------------------------------------------------------- |
    //| `supplyAsync(Supplier)` | Runs a task asynchronously and returns a future with a value      |
    //| `runAsync(Runnable)`    | Same as above but returns a `CompletableFuture<Void>` (no result) |


    public static void main(String[] args) {
        CompletableFutureIntro object = new CompletableFutureIntro();

        CompletableFuture
                .supplyAsync(() -> object.externalService()) // the aysnc process will start
                .thenApply((r)->r*4) // get the result and then do some tranformations
                .thenAccept((ans) -> System.out.println("a is done" + ans));


        CompletableFuture<Integer> a = CompletableFuture
                .supplyAsync(() -> object.externalService()); // here we can simulate a call to an external service
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 10);
        b.thenAccept((ans)-> System.out.println("b is done"+ans));

        ///  now these 2 tasks are done aysnc in parallel
        // we can combine the reultsn of a and b
      CompletableFuture<Integer> sum= a.thenCombine(b,(x,y)->x+y);

        try {
            System.out.println("sum: "+sum.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        // handle exception
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
        if(true){
         throw new RuntimeException("failed to call external service");
        }
        return 200;
        }).exceptionally(ex->{
            System.out.println("Handled: " + ex.getMessage());
            return -1;
        });

        System.out.println(result.join());
    }


    public int externalService(){
        try{
            Thread.sleep(5000);
            return 5;
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}

