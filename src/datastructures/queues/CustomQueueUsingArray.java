package datastructures.queues;

public class CustomQueueUsingArray<T>{

    private T[] array;

    private static final Integer DEFAULT_SIZE=3;

    private int count=0;

    public CustomQueueUsingArray() {
        this.array = (T[])new Object[DEFAULT_SIZE];
    }

    private void enqueue(T data)
    {
        if(isFull())
        {
            resize(count*2);
        }
     array[count++]=data;
    }

    private T dequeue() throws Exception
    {
        if(isEmpty())
        {
            throw  new RuntimeException("Queue is empty");
        }
        T dataRemoved = array[0];
        // shifting the elements till the end of the array  O(n)
        for(int i=1;i<count;i++)
        {
            array[i-1]=array[i];
        }

        count--;
        return dataRemoved;
    }

    // peek shows the element at front
    private T peek()
    {
        if(isEmpty())
        {
            throw  new RuntimeException("Queue is empty");
        }
        return array[0];
    }

    private boolean isFull() {
        return count==array.length;
    }

    private boolean isEmpty()
    {
        return count==0;
    }
    private int size()
    {
        return count;
    }

    private void display()
    {
        for(int i=0;i<count;i++)
        {
            System.out.println(array[i]+" ");
        }
    }


    private void resize(final int size) {
        // arrays are not dynamic this will take o(n) complexity to make the array resuze when we dequue an element
        T[] copyArray = (T[]) new Object[size];

        // copy the elements from old to new array
        for(int i=0;i<array.length;i++)
        {
            copyArray[i]=array[i];
        }
        this.array=copyArray;
    }


    public static void main(String[] args) throws Exception {

        CustomQueueUsingArray<Integer> customQueueUsingArray = new CustomQueueUsingArray<>();
        for(int i=1;i<=3;i++)
        {
            customQueueUsingArray.enqueue(i*10);
        }

        System.out.println(customQueueUsingArray.peek());

        System.out.println(customQueueUsingArray.dequeue());
        System.out.println(customQueueUsingArray.dequeue());

        System.out.println("display");
        customQueueUsingArray.display();
    }
}
