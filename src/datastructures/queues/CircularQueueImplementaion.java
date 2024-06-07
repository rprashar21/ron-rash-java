package datastructures.queues;

public class CircularQueueImplementaion<T> {

    // circular queue deals with modulo and same as rotation of an array
    // fromt pointer and end pointer use frnt and en

    // [10,20,30,40]

     protected T[]  array;
     private static final int DEFAULT_SIZE=3;
     private int count=0;

     private int front=0;
     private int end=0;

     public CircularQueueImplementaion(){
         this.array =(T[]) new Object[DEFAULT_SIZE];

     }

     private boolean enqueue(T data)
     {
         if(isFull())
             return false;
         array[end++]=data;
         end = end % array.length; // this is done so that when we reach the end of the circular array
         count++;
         return true;
     }

     private T dequeue(){

         T dataRemoved = array[front++];
         front= front%array.length;
         count--;
         return dataRemoved;
     }

     private T peek()
     {
        return array[front];
     }

     private void display()
     {

     }

     private boolean isFull()
     {
         return this.count==array.length;
     }

    public static void main(String[] args) {
        CircularQueueImplementaion circularQueueImplementaion = new CircularQueueImplementaion();

        for(int i=1;i<=3;i++)
        {
            circularQueueImplementaion.enqueue(i*10);
        }

        System.out.println(circularQueueImplementaion.peek());

        System.out.println(circularQueueImplementaion.dequeue());
        circularQueueImplementaion.enqueue(40);
        System.out.println(circularQueueImplementaion.dequeue());

        System.out.println("display--------------|||||||");
        circularQueueImplementaion.display();
    }

}
