package com.corejava.corejava.immutables;

public class RealExample implements Runnable {
    private final ConfigReader configReader;

    public RealExample(ConfigReader configReader) {
        this.configReader = configReader;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +
                           " - Reading env: " + configReader.env() + ", timeout: " + configReader.timeout());
    }

    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader("PROD",30);

        // simulate concurrecny env
        Thread t1 = new Thread(new RealExample(configReader));
        Thread t2 = new Thread(new RealExample(configReader));

        t1.start();
        t2.start();

        // output
        // Reader-1 - Reading env: PROD, timeout: 30
        //Reader-2 - Reading env: PROD, timeout: 30

        //| Feature       | Benefit                                        |
        //| ------------- | ---------------------------------------------- |
        //| `record`      | Immutable, clean syntax                        |
        //| Shared safely | No locks, no risk of corruption                |
        //| Fast and safe | Perfect for concurrent read-heavy use cases    |
        //| Minimal code  | Less boilerplate than Lombok or manual classes |
    }

}

record ConfigReader(String env,int timeout){
    // this will automatcalli give private final fields and constructor
    // getters for the 3 fileds
    // equals hashcode tostring

}
