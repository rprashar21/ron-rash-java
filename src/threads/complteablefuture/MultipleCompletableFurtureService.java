package threads.complteablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleCompletableFurtureService {

    //supplyAyscny method
    // thenCombine method  -completion methods which combine two or more aysnc calls
    // thenApply - this method works on the previos aysnc call like chaingi
    // thenAccept -- this is the final completion stage

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MultipleCompletableFurtureService service = new MultipleCompletableFurtureService();
        // suppose a service output depend upon 2 external services like  hello and world
        String hello = ExternalService.hello(); // this is a bocking call callled by main thread
        String world = ExternalService.world();

        System.out.println(hello + world);

        // Non locking nature with CompletableFuture where will combine the calls from 2 different services
//
//        String result = aysncCall(service);
//        System.out.println(result);

//        CompletableFuture<String> stringCompletableFuture = showForkJoinInTheBackGroun();
//
//        String s = stringCompletableFuture.get();
//        System.out.println("final result is "+s);

        CompletableFuture<String> stringCompletableFuture = asyncWorkingWithExecutoservice();

        stringCompletableFuture.join();


    }

    private static String aysncCall(MultipleCompletableFurtureService service) {

        // this is a blocking call .. suppose this is calling an external service hello
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> ExternalService.hello());

        // this is another independednat call
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> ExternalService.world());

        // now i have to combine these results
        return helloFuture
                .thenCombine(worldFuture, (h, w) -> h + w)
                .thenApply(String::toUpperCase)
                .join();// use this only when we are returning something

    }

    // this is how we should return when we get a client call
    private static CompletableFuture<String> getAsyncCall(MultipleCompletableFurtureService service) {

        // this is a blocking call
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> ExternalService.hello());

        // this is another independednat call
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> ExternalService.world());

        // now i have to combine these results
        return helloFuture
                .thenCombine(worldFuture, (h, w) -> h + w)
                .thenApply(String::toUpperCase);// use this only when we are returning something

    }


    // here we are Service A is calling Service B, servie b is deoenddant on service A
    private static CompletableFuture<String> getAsyncCallWithMultipleServices(MultipleCompletableFurtureService service) {

        // this is a blocking call here we are Service A is calling Service B, servie b is deoenddant on service A
        CompletableFuture<String> helloFuture = CompletableFuture
                .supplyAsync(() -> ExternalService.hello())
                .thenApply((s) -> {
                    String s1 = updateOrCallAnotherService(s);
                    return s1;
                }) // takes the input from previuos aysnc all

                ;

        // this is another independednat call
        CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> ExternalService.world());

        // now i have to combine these results
        return helloFuture
                .thenCombine(worldFuture, (h, w) -> h + w)
                .thenApply(String::toUpperCase);// use this only when we are returning something

    }

    // this has multiple completable calls
    private static CompletableFuture<String> getAsyncMultipleCalls() {

        CompletableFuture<String> firstCall = CompletableFuture.supplyAsync(() -> ExternalService.hello())
                .thenApply(s -> s.toUpperCase());

        CompletableFuture<String> secondCall = CompletableFuture.supplyAsync(() -> ExternalService.world())
                .thenApply(s -> s.toLowerCase());

        CompletableFuture<String> thirdCall = CompletableFuture.supplyAsync(() -> ExternalService.helloWorld())
                .thenApply(s -> s.substring(0, 2));

        // combine all these calls

        CompletableFuture<String> finalResult = firstCall
                .thenCombine(secondCall, (first, second) -> first + second)
                .thenCombine(thirdCall, (prev, current) -> prev + current)
                .thenApply(String::trim);
        return finalResult;

    }

    //  it normally runs in  pool of threads the below example shows us that how it will depend upon the number ofcores

    private static CompletableFuture<String> showForkJoinInTheBackGroun() {

        System.out.println(">>showForkJoinInTheBackGroun ");
        System.out.println(Thread.currentThread().getName());

        CompletableFuture<String> firstCall = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("async call"+Thread.currentThread().getName());
                  return   ExternalService.hello();

                })
                .thenApply(s -> {
                    System.out.println("then apply "+Thread.currentThread().getName());
                   return s.toUpperCase();
                });
        CompletableFuture<String> secondCall = CompletableFuture.supplyAsync(() -> ExternalService.world())
                .thenApply(s -> s.toLowerCase());

        CompletableFuture<String> thirdCall = CompletableFuture.supplyAsync(() -> ExternalService.helloWorld())
                .thenApply(s -> s.substring(0, 2));

        // combine all these calls

        CompletableFuture<String> finalResult = firstCall
                .thenCombine(secondCall, (first, second) -> first + second)
                .thenCombine(thirdCall, (prev, current) -> prev + current)
                .thenApply(String::trim);
        return finalResult;

    }

    private static CompletableFuture<String> asyncWorkingWithExecutoservice() {

        System.out.println(">> asyncWorkingWithExecutoservice ");

      //  ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletableFuture<String> firstCall = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("async call "+Thread.currentThread().getName());
                    return   ExternalService.hello();

                },service) // executor service is passed an argument
                .thenApply(s -> {
                    System.out.println("then apply "+Thread.currentThread().getName());
                    return s.toUpperCase();
                });

        // now they will all execute in a different thread pool
        CompletableFuture<String> secondCall = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("second call "+Thread.currentThread().getName());
                    return ExternalService.world();
                    },service)
                .thenApply(s -> s.toLowerCase());

        CompletableFuture<String> thirdCall = CompletableFuture.supplyAsync(() -> ExternalService.helloWorld())
                .thenApply(s -> s.substring(0, 2));

        // combine all these calls

        CompletableFuture<String> finalResult = firstCall
                .thenCombine(secondCall, (first, second) -> first + second)
                .thenCombine(thirdCall, (prev, current) -> prev + current)
                .thenApply(String::trim);
        return finalResult;

    }


    private static String updateOrCallAnotherService(final String s) {

        String someString = ExternalService.anotherExternalServiceCall();
        return someString;
    }


}

