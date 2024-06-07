package com.corejava.corejava.collectionss.lists;

import java.util.ArrayList;
import java.util.List;

public class CustomList {

    // custom list will have add remove and get methods all are based on the index
    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        integers.add(10);// adds to the end of the list
        integers.add(1, 30);// this will add object at index 1
        integers.remove(1); // remove object at index 1
        System.out.println(integers);

        ;// lets test ur custom array
        ArrayListSample<Integer> listSample = new ArrayListSample<>();

        for (int i = 1; i <= 10; i++) {
            int value = i * 10;
            listSample.add(value);
        }

        Integer integer = listSample.get(9);
        System.out.println(integer);
        listSample.displayElementsAtPostion();
        listSample.remove(6);
        listSample.displayElementsAtPostion();

    }
}

class ArrayListSample<T> {

    private T[] array;
    private int count;
    private static final Integer SIZE = 5;

    public ArrayListSample() {
        this.array = (T[]) new Object[SIZE];
    }

    public void add(T data) {
        // check if the internal array is full or not
        if (isFull()) {
            resizeArray(); // need to resize the array
            array[count] = data;
        }
        array[count] = data;
        count++;
    }

    public T get(int index) {
        return array[index];
    }

    public void remove(int index) {
        // copy the elements to a new array except the element at this index
        T[] temp = (T[]) new Object[array.length - 1];
        int k=0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == array[index])
                continue;
            else {
                temp[k++] = array[i];
            }

        }
        array=temp;
    }

    private void resizeArray() {
        T[] temp = (T[]) new Object[2 * array.length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void displayElementsAtPostion()
    {
        for(int i=0;i<array.length;i++)
        {
            System.out.print(i+" : "+array[i]+"   ");
        }
        System.out.println();
    }


    private boolean isFull() {
        return count == array.length;
    }
}