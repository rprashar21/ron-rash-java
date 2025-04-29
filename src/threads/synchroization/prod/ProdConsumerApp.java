package threads.synchroization;

// shared object
class Counter{
    private int count;
    // this will check if the producers has produce and consumer has consumed
    private boolean status;

    // this method will be used by the producer to produce
    synchronized public void setCount(int count) {
        this.count = count;
    }
    // this method will be used by the consumer to consume
    synchronized public int getCount(){
        return count;
    }
}


class









public class ProdConsumerApp {
}
