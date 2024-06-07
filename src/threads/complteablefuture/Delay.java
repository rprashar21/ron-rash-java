package threads.complteablefuture;

public class Delay {

    public static void delay(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
