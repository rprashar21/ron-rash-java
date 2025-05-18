package threads.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SampleThreadPool {


    public static void main(String[] args) {

        // suppose i have a list of tasks to make sure that all the documents are downloaded from a url
        List<String> documentList = Arrays.asList("A","B","C","D","E","F","G","H");

        // now I will create a thread pool to perform each task and convert/dowload from a give url and not block the cal
        // here i will convert t lowercase

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<String>> futures = new ArrayList<>();

        // now for each url or uppercase letter i will submit to a thread in executorservice
        // once they are don i will block the call and retrieve

        for(String s : documentList) {
            // here im submitting my task to the executor service and then putting it in a list of futures
            // this is a non blocking call
            Future<String> submit = executor.submit(() -> s.toLowerCase());
            futures.add(submit);
        }

        // now i will block the call

        // this can be an assertion
        for(Future<String> future : futures) {
            try{
                System.out.println(future.get());
            }catch (Exception e) {

            }
        }

    }
}
