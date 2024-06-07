package threads.synchroization;

import java.util.ArrayList;
import java.util.List;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        Thread t1 = new Thread(new Producer(integerList));
        Thread t2 = new Thread(new Consumer(integerList));

        t1.start();
        t2.start();

    }
}


class Producer implements Runnable {

    List<Integer> list;

    public Producer(final List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {

        synchronized (list) { // synchronized block
            try {
                while (true) {
                    if (!list.isEmpty()) {
                        list.wait(); // it will wait for notification
                    } else {
                        for (int i = 0; i <= 5; i++) {
                            System.out.println("Adding element to the list"+i);
                            Thread.sleep(1000);
                            list.add(i);
                        }
                        list.notifyAll();     // release the lock of the list
                    }

                }
            }catch (InterruptedException e){}

        }
    }
}

class Consumer implements Runnable {
    List<Integer> list;

    public Consumer(final List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list) { // synchronized block

      try{
          while (true){
              if(list.isEmpty())
                  list.wait();
              else {
                  // it will consume message
                  while(!list.isEmpty()){
                      System.out.println("consuming message "+list.remove(0));
                  }
                  list.notifyAll(); // now release the lock of the object
              }
          }
      }catch (InterruptedException e){}
        }
    }
}