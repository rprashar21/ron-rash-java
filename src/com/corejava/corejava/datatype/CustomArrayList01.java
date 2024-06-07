package com.corejava.corejava.datatype;

import java.util.Arrays;

public class CustomArrayList01 {

    // add(int value) no return
    // remove(int index) no return
    // get(int index) return in

    private int[] array;
    private int size = 0;

    public CustomArrayList01() {
        this.array = new int[5];
    }

    public void add(int value) {
        if (isFull()) {
            resize();
        }
        array[size++] = value;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(" Out of range element");

        return array[size - 1];
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(" Out of range element");
        // shifting the elements [1,2,3,4] -- []
        for (int i = index; i < size - 1; i++) {

            array[i] = array[i + 1];
        }
        size--;
    }

    private void resize() {
        int temp[] = Arrays.copyOf(array, array.length * 2);
        array = temp;
    }

    private boolean isFull() {
        return this.size == array.length;
    }

    public static void main(String[] args) {
        CustomArrayList01 al = new CustomArrayList01();
        for (int i = 0; i < 5; i++) {
            al.add((int) (Math.random() * 9000));
        }
        System.out.println(al);
        al.remove(4);
        System.out.println(al.get(3));
        System.out.println(al);

    }

}
