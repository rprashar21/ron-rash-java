package datastructures.stacks;

public class CustomStack02<T>{
    // stack implementation Using an array;

    private T[]  array;
    private int count=0;

    public CustomStack02() {
        array = (T[]) new Object[1];
    }
    // here we are just adding the data to the end of the array 0(1)
    private void push(T data){

        // arrays are not dynamic data structures we have to resize if the array is full
        if(isFull())
        {
           resize(2*count);
        }
        array[count++]=data;
    }
    private T pop(){


        if(isEmpty())
            return null;
        T item = array[--count];
        array[count]=null;

        // resize the array again with less size
        if(count > 0 && count == array.length/4)
            resize(count/2);
        return item;
    }

    private boolean isFull() {
        return array.length==count;
    }

    private void resize(int size) {
        T[] newArray = (T[])new Object[size];
        // copy the elements from old array to new array
        for(int i=0;i<count;i++)
        {
            newArray[i]=array[i];
        }
        this.array=newArray;
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count==0;
    }

    public static void main(String[] args) {
        CustomStack02<Integer> customStack02 = new CustomStack02<>();
        customStack02.push(10);
        customStack02.push(20);
        customStack02.push(30);

        System.out.println(customStack02.pop());
        System.out.println(customStack02.pop());
        System.out.println(customStack02.pop());
    }
}
