package threads.complteablefuture.practice;

import java.util.concurrent.CompletableFuture;

public class CompPractice1 {


    // scenario 1 where we perform aysn operations
    private static final String API_1 = "https://example1.com";
    private static final String API_2 = "https://example2.com";
    private static final String API_3 = "https://example3.com";

    public static void main(String[] args) {


        // some operatins of calling external api
        // these task will be done asynchronously
        //Each of these calls is independent — you fire all three in parallel.
        // That’s great when one API doesn’t depend on the result of another.
        CompletableFuture<Integer> api1 = getApi(API_1);
        CompletableFuture<Integer> api2 = getApi(API_2);
        CompletableFuture<Integer> api3 = getApi(API_3);

        // wait for all of it to complete
        CompletableFuture.allOf(api1, api2, api3).join();
        System.out.println("All three complete " + api1.join() + "/" + api2.join() + "/" + api3.join());


        //  combine
        CompletableFuture<Integer> sum = api1.
                thenCombine(api2, (a, b) -> a + b).
                thenCombine(api3, (ab, c) -> ab + c);

        System.out.println("Result of all three stock prices " + sum.join());


        // compose
        //Use thenCompose() when one async call depends on the result of a previous async call.
        CompletableFuture<Integer> future = getUserIdDbCall("Rohit").thenCompose(userId -> getCreditScore(userId));

        System.out.println("Result  credit score is " + future.join());


        ///  example fethc userID from db and then using that fethc the credutscore and see if the user is fit for credit card


    }

    public static CompletableFuture<Integer> getApi(String url) {
        return CompletableFuture.supplyAsync(() -> {

                    try {
                        return externalCall(url);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return 0;
        });
    }

    public static int externalCall(String url) throws InterruptedException {

        // simulate external call
        switch (url) {
            case API_1 -> {
                Thread.sleep(2000);
                return 10;
            }
            case API_2 -> {
                throw new RuntimeException("External Call Failed");
//                Thread.sleep(3000);
//                return 20;
            }
            case API_3 -> {
                Thread.sleep(3000);
                return 30;
            }
            default -> {
                return -1;
            }
        }
    }

    public static CompletableFuture<Integer> getUserIdDbCall(String userName) {

        return CompletableFuture.supplyAsync(() -> {
            // simulate db call
            System.out.println("Simulating db call for user " + userName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 210;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public static CompletableFuture<Integer> getCreditScore(int userInd) {

        return CompletableFuture.supplyAsync(() -> {
            // simulate db call
            System.out.println("Simulating credit call  for userId " + userInd);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 800;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }
}