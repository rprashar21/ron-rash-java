package threads.complteablefuture.scenarios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureScenarios {


    // call from 3 different apis and stock price and sumd the value
    // Assume one faile what tp do

    // importance of this style of  coding is
//    Resilient to API failures → Uses fallback values.
//      ✅ Asynchronous calls improve performance → Aggregates data in parallel.
//            ✅ Ensures fault tolerance → exceptionally() prevents crashes.

    public static void main(String[] args) {

        // these 3 are non blocking calls
        CompletableFuture<BigDecimal> ap1 = fetchStockPrice("AP1");
        System.out.println("next call to api 2");
        CompletableFuture<BigDecimal> ap2 = fetchStockPrice("AP2");
        CompletableFuture<BigDecimal> ap3 = fetchStockPrice("AP3");

        // lets join the calls to get a value
        CompletableFuture<BigDecimal> avgPrice = ap1.thenCombine(ap2, (a, b) -> a.add(b))
                .thenCombine(ap3, (a, b) -> getFinalPrice(a, b));

        // this will make the main thread wait
        System.out.println("final Stock price is " + avgPrice.join());

    }

    private static BigDecimal getFinalPrice(final BigDecimal a, final BigDecimal b) {
        return a.add(b).divide(BigDecimal.valueOf(3), RoundingMode.HALF_UP);


    }

    private static CompletableFuture<BigDecimal> fetchStockPrice(String url) {

        // simulate  the call

        return CompletableFuture.supplyAsync(() -> externalCall(url)).exceptionally(ex -> {
            System.err.println(ex.getMessage());
            System.out.println("Error occurred while trying to fetch stock price");
            return BigDecimal.valueOf(100.0); // default stock price
        });
    }

    private static BigDecimal externalCall(String url) {

        switch (url) {
            case "AP1" -> {
                try {
                    Thread.sleep(5000);
                    return BigDecimal.valueOf(500.0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            case "AP2" -> {
                throw new ArithmeticException("ArithmeticException");
              //  return BigDecimal.valueOf(50.0);
            }
            case "AP3" -> {
                return BigDecimal.valueOf(200.0);
            }
            default -> {
                return BigDecimal.valueOf(10);
            }
        }
    }
}
