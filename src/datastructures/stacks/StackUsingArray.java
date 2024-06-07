package datastructures.stacks;

public class StackUsingArray<T> {

    public T[] array;
    private static final int DEFAULT_SIZE=3;

    public int count=0;

    public StackUsingArray() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    public void push(T data)
    {
        if(isFull())
        {
            resize(count*2);
        }
        array[count++]=data;
    }

    public T pop(){
        // remove the elemet at current count
        T dataToBeRemoved = array[--count];

     // reduce the size of the array
        if(count > 0 && count == array.length/4)
            resize(count/2);
        return dataToBeRemoved;
    }

    public T peek(){
        return array[count];
    }

    private void resize(final int size) {
        // arrays are not dynamic
        T[] copyArray = (T[]) new Object[size];

        // copy the elements from old to new array
        for(int i=0;i<array.length;i++)
        {
            copyArray[i]=array[i];
        }
        this.array=copyArray;
    }


    private boolean isFull() {
        return count==array.length;
    }

    private int size()
    {
        return count;
    }


    public static void main(String[] args) {

        StackUsingArray<Integer> stackUsingArray = new StackUsingArray<>();
        System.out.println(        stackUsingArray.peek());

        for(int i=1;i<=10;i++)
        stackUsingArray.push(i*10);

        System.out.println(stackUsingArray.pop());
        System.out.println(stackUsingArray.pop());
        System.out.println(stackUsingArray.pop());

        System.out.println(stackUsingArray.size());
    }
}
