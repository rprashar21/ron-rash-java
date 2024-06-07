package threads.complteablefuture;


// this class acts as an external service calling external services and getting data
public class ExternalService {
    public static String helloWorld() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException exception) {

        }
        return "hello world ";
    }

    public static String hello() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException exception) {

        }
        return "hello ";
    }

    public static String world() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException exception) {

        }
        return "world";
    }

    public static String anotherExternalServiceCall() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException exception) {

        }
        return "external call";
    }
}
