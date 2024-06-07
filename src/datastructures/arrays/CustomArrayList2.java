package datastructures.arrays;

import java.util.Arrays;

public class CustomArrayList2<T> {

    private Object[] array;
    private int size;
    private static final int DEFAULT_SIZE = 3;

    // constructor
    public CustomArrayList2() {
        this.array = new Object[DEFAULT_SIZE];
    }

    public void add(T data) {

        if (isArrayFull()) {
            // if the array is full resize it
            resize();
        }
        this.array[size++] = data;
    }

    private boolean isArrayFull() {
        return this.size == this.array.length;
    }

    private void resize() {
        // create a temporary array and copy all the value from current array to this temporary array
        T[] tempArray = (T[]) new Object[array.length * 2];

        tempArray = (T[]) Arrays.copyOf(array, tempArray.length);

        array = tempArray;
    }

    public void remove(int index) {
        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException();
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    public Object get(int index) {
        if (index < 0 || index > this.array.length)
            throw new IndexOutOfBoundsException();

        return this.array[index];
    }

    public int size(){
        return this.size;
    }
    public void set(int index,Object value ){
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        array[index]=value;
    }

    public void print(){
        System.out.println();
        for(int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }



    public static void main(String[] args) {
        CustomArrayList2<Integer> customArrayList2 = new CustomArrayList2<>();
        customArrayList2.add(10);
        customArrayList2.add(20);
        customArrayList2.add(30);
        customArrayList2.add(40);
        customArrayList2.print();
        customArrayList2.get(3);
        customArrayList2.remove(3);
        customArrayList2.print();
        customArrayList2.set(1,500);
        customArrayList2.print();

    }
}
