package threads.complteablefuture.scenarios;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DataParralelProcessing {

    public static void main(String[] args) {

        //  Cleanse the data (remove duplicates, invalid values).
        //Enrich the data (add metadata, location info).
        //Aggregate the data (compute averages, detect anomalies

//        Each stage runs independently in parallel using supplyAsync().
//                The final result is computed using thenCompose() to ensure dependent transformations.

        //    In Java's CompletableFuture, thenCompose() is used for chaining dependent asynchronous calls —
        //    when the second async task depends on the result of the first one

//        Real-life Analogy
//        Suppose:
//
//        You fetch a userId
//
//        Then you fetch user details using that userId
//
//        This is a perfect use case for thenCompose()


        List<String> datafromIOTDevices = List.of("data1", "data2", "data3", "data4", "invalid", "data6");
        CompletableFuture<List<String>> cleanData = CompletableFuture.supplyAsync(() -> cleanData(datafromIOTDevices));

    }

    private static List<String> enrichTheCleanData(final List<String> stringdata) {

        return stringdata.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
    }

    private static List<String> cleanData(List<String> data) {
        return data.stream().filter(d -> !d.contains("invalid")).toList();

    }

}
