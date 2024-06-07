package com.corejava.Generics.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomArrayList {

    // add method get defaultSize 10
    // lets create an array
    private int[] data;
    private static int DEFUALT_SIZE = 10;
    private int size = 0;

    public CustomArrayList() {
        this.data = new int[DEFUALT_SIZE];
    }

    // add method
    public void add(int value) {
        if (isFull()) {
            resize();
        }
        data[size++] = value;
    }
    // remove method
    public int remove()
    {
        // remove the last item
       int removed = data[--size];
       return removed;
    }
    public void set(int index, int value)
    {
        if(index<=size)
        data[index]=value;
    }

    private void resize() {
        // array size is 10 make it double the size
        int[] temp = new int[data.length * 2];

        // copy the current items to this new temp array
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isFull() {
        return data.length == size;
    }

    public static void main(String[] args) {
        CustomArrayList customArrayList = new CustomArrayList();
        customArrayList.add(10);
        customArrayList.add(20);
        customArrayList.add(30);
        customArrayList.remove();

        System.out.println(customArrayList);

        ArrayList<Integer> arrayList = new ArrayList<>();


    }

    @Override
    public String toString() {
        return "CustomList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
