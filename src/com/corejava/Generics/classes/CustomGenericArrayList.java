package com.corejava.Generics.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomGenericArrayList<T> {

    // add method get defaultSize 10
    // lets create an array
    private Object[] data;
    private static int DEFUALT_SIZE = 10;
    private int size = 0;

    public CustomGenericArrayList() {
        this.data = new Object[DEFUALT_SIZE];
    }

    // add method
    public void add(int value) {
        if (isFull()) {
            resize();
        }
        data[size++] = value;
    }
    // remove method
    public Object remove()
    {
        // remove the last item
       return data[--size];
    }


    public void set(int index, int value)
    {
        if(index<=size)
        data[index]=value;
    }

    private void resize() {
        // array size is 10 make it double the size
        Object[] temp = new Object[data.length * 2];

        // copy the current items to this new temp array
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isFull() {
        return data.length == size;
    }



    @Override
    public String toString() {
        return "CustomList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
