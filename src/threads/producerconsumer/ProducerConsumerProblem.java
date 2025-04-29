package threads;

public class ProducerConsumerProblem {

//    We need to handle resource sharing and synchronization to solve a few complexities:
//
//    Synchronization on queue while adding and removing data
//    On queue empty, the consumer has to wait until the producer adds new data to the queue
//    When the queue is full, the producer has to wait until the consumer consumes data and the queue has some empty buffer

    // In Java threads use blockingQueue

    // article for threads
    // https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-concurrency-advanced-4

    // this is the shared object between two threads That is the producer thread and the consumer therad
    public static void main(String[] args) {
        Message message = new Message();
        Thread producerThread = new Thread(new Producer(message));
        Thread consumerThread = new Thread(new Producer(message));

        producerThread.start();
        consumerThread.start();
    }



    // start these two threads
}

class Message {
    private String message;
    public boolean isEmpty = true;

    // this is used by the consumer to read messages
    public synchronized String read() {
        while (isEmpty) {
            // read the messages
        }
        isEmpty = true;
        return message;
    }

    public synchronized void write(String message) {
        while (!isEmpty) {
            // write the messages to the queue
        }
        isEmpty = false;
        this.message=message;
    }
}

class Producer implements Runnable {

    private Message message;

    public Producer(final Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        String messages[] = {"hello John ", "How are You ?", "Im fine ", "Thanks for asking"};
        for (String msg : messages) {
            message.write(msg);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {

            }
        }
        message.write("FINISHED");
    }
}

class Consumer implements Runnable {

    private Message message;

    public Consumer(final Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        while(!message.isEmpty){
            String read = message.read();
            System.out.println(read);
            if(read.equals("FINISHED")){
                break;
            }
        }
    }
}
