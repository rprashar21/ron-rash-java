package threads.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolRunnableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // lets create a list of actions to perform
        Action action1 = new Action("Cleaning room");
        Action action2 = new Action("Making Lunch");
        Action action3 = new Action("Watching match");

        List<Action> actionList = Arrays.asList(action1, action2, action3);

        // create an ExecutorService
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        // we will su
        List<Future> futureList = new ArrayList<>();

        // we will submit these tasks to the executor pool whihc will assign these threads a atsk
        // Submit them all first, collecting their Futures:
        for (Action action : actionList) {
            futureList.add(service.submit(action));
        }

        for (Future future : futureList) {
            // this is a blocking call
            future.get();
        }

    // shutdown the service
        service.shutdown();

    }
}

class Action implements Runnable {

    private String name;

    public Action(final String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Action being excuted is :: " + this.name + "::" + Thread.currentThread().getName());
            Thread.sleep(2000);
            for(int i=0;i<3;i++)
            {
                // perfrom some task maybe some http call or i/o operation wait for some time
            }

            System.out.println("Action "+ this.name+" excluded");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
