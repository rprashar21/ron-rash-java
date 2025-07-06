package threads.complteablefuture.scenarios;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelScenario {

    // user places an order and there are multiple tasks to be completed
    // validate the order , process the payment, update inventory

    // the difference between runAsync and supplyAsync is that
    //                  does nt return value   .. it returns a value

//    All tasks run in parallel → Reduced response time.
//✅ Non-blocking approach → Uses thread pools efficiently.
//            ✅ Ensures order consistency → Proceeds only after all tasks complete.

    public static void main(String[] args) {
        doInParallel();

        orderProcessinginSequenceUsingCompletabaleFuture();
    }

    private static void orderProcessinginSequenceUsingCompletabaleFuture() {
        System.out.println("This will maintain the order processing sequence");

        // we can run them in sequence also using completablefuture
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> orderProcessing = CompletableFuture.runAsync(() -> {
                    simulateDelay(1500);
                    System.out.println("Order validated successfully");
                    //  throw new RuntimeException("order 123 not processed successfully");
                }, executorService)
                .thenRunAsync(() -> {
                    simulateDelay(1500);
                    System.out.println("Payment validate processed");
                }, executorService)
                .thenRunAsync(() -> {
                    simulateDelay(1500);
                    System.out.println("Email sent successfully");
                });
        try {
            orderProcessing.join();
            System.out.println("Order successfully processed!");
        } catch (Exception e) {
            System.out.println("🛑 Order processing aborted due to error: " + e.getCause().getMessage());

        }
        executorService.shutdown();
    }

    private static void doInParallel() {
        CompletableFuture<Void> orderValidation = CompletableFuture.runAsync(() -> {
            simulateDelay(6000);
            System.out.println("✅ Order validated");
        });
        CompletableFuture<Void> paymentProcessing = CompletableFuture.runAsync(() -> {
            simulateDelay(1500);
            System.out.println("💰 Payment processed");
        });
        CompletableFuture<Void> inventoryUpdate = CompletableFuture.runAsync(() -> {
            simulateDelay(1200);
            System.out.println("📦 Inventory updated");
        });
        CompletableFuture<Void> confirmationEmail = CompletableFuture.runAsync(() -> {
            simulateDelay(500);
            //  throw new RuntimeException("some exception");
            // System.out.println("📧 Confirmation email sent");
        });
        // Wait for all tasks to complete
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(orderValidation, paymentProcessing, inventoryUpdate, confirmationEmail);
        allTasks.join();
        System.out.println("🚀 Order successfully processed!");
    }

    private static void simulateDelay(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
