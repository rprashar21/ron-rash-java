package threads.complteablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureComparsion {
    public static void main(String[] args) {

        ExternalCall externalCall = new ExternalCall();
        // First we call synchronous sequentially
//        System.out.println("synch sequemtial");
//        long startTime = System.currentTimeMillis();
//        String external1 = externalCall.callExternal();
//        String external2 = externalCall.callExternal2();
//
//        System.out.println(external1 + " ----- " + external2);
//        System.out.println(System.currentTimeMillis() - startTime + " milliseconds");

        System.out.println("aysnc call");

        long startTime1 = System.currentTimeMillis();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> externalCall.callExternal());
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> externalCall.callExternal2());

        System.out.println("performin some other task");

         stringCompletableFuture.thenCombine(stringCompletableFuture1,(a,b)->a+b).thenAccept(System.out::println);

        System.out.println(System.currentTimeMillis() - startTime1 + " milliseconds");

    }
}

class ExternalCall {

    public String callExternal() {
        System.out.println("simulating an external call");
        try {
          Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.println("handle exception to external call");
        }
        return "value from external call";
    }

    public String callExternal2() {
        System.out.println("simulating an external call 2");
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            System.out.println("handle exception to external call 2");
        }
        return "value from external call 2";
    }
}