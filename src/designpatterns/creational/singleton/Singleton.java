package designpatterns.creational.singleton;

public class Singleton {
    /*
     * Singleton --- one instance of the class
     * Usage -- > logging,Cache,Session and Drivers
     *
     * How to create Singleton ??
     * Constructors should be private -- so that on object is create inside the same class
     * public method to return the instance of the class
     * Instance type should  be private static
     *
     *   Initilaisation-Types
     *  Eager/Lazy/Thread Safe/Thread Safe Block Initialization
     *
     * */

    public static void main(String[] args) {

        // Eager Initialization
        SingletonEager singletonInstance1 = SingletonEager.getInstance();
        SingletonEager singletonInstance2 = SingletonEager.getInstance();
        SingletonEager singletonInstance3 = SingletonEager.getInstance();

        System.out.println(singletonInstance1.hashCode() + " " + singletonInstance2.hashCode() + " " + singletonInstance3.hashCode());

        // Lazy Initialization
    }
}

class SingletonEager {
    private static SingletonEager singletonEager = new SingletonEager();

    private SingletonEager() {
        // object cannot be created from outside this class
    }

    public static SingletonEager getInstance() {
        return singletonEager;
    }
}

class SingleTonLazy {
    private static SingleTonLazy instance;

    private SingleTonLazy() {
    }

    public static SingleTonLazy getInstance() {
        if (instance == null)
            instance = new SingleTonLazy();

        return instance;
    }
}

class SingleTonSynchronized {
    private static SingleTonSynchronized instance;

    private SingleTonSynchronized() {
    }

    // so when one thread is accessing other thread will not be able to access thisc
// only
    public static synchronized SingleTonSynchronized getInstance() {
        if (instance == null)
            instance = new SingleTonSynchronized();

        return instance;
    }
}

class SingleTonSynchronizedBlock {
    private static SingleTonSynchronizedBlock instance;

    private SingleTonSynchronizedBlock() {
    }

    // instead of blocking the entire method and read operations only block the write operations
    public static SingleTonSynchronizedBlock getInstance() {
        if (instance == null)
            synchronized (SingleTonSynchronizedBlock.class) {
                if (instance == null)
                    instance = new SingleTonSynchronizedBlock();
            }

        return instance;
    }
}
