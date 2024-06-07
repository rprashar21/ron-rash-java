package datastructures.arrays.custom;

import java.util.Arrays;

class CustomArray {


    // arraylist has get(),size(),remove,set(),add
    // internally this is an array
    private int[] array;
    private int size = 0;

    public CustomArray(final int size) {
        array = new int[size];
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index out of range");
        }
        return array[index];
    }
    public int size()
    {
        return size;
    }

    public void remove(int index)
    {
        // size should always be 1 less that current size
        for(int i=index;i<size-1;i++)
        {
            array[i]=array[i+1];
        }
        size--;
    }

    public void add(int data)
    {
        if(isArrayFull())
        {
            resize();
            array[size]=data;
        }
        else {
            array[size]=data;
        }
        size++;
    }

    private void resize() {
        int[] temp = new int[array.length*2];
        // copy all the lement into this
        for(int i=0;i<size;i++)
        {
            temp[i]=array[i];
        }
        array=temp;
    }

    private boolean isArrayFull() {
        return size==array.length;
    }

    public void print()
    {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}

public class TestCustomArray {
    public static void main(String[] args) {

        CustomArray customArray = new CustomArray(5);
        for(int i=0;i<10;i++)
        {
            customArray.add(i+1*10);
        }
        customArray.print();
        customArray.remove(0);
        customArray.print();
        customArray.remove(customArray.size());

        customArray.print();
    }
}
