package datastructures.arrays.custom;

public class CustomArray2 {
    private int[] array;
    private int size;

    public CustomArray2(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return array[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        array[index] = value;
    }

    public void add(int value) {
        if (size == array.length) {
            // If the underlying array is full, create a new array with increased capacity
            int[] newArray = new int[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public int size() {
        return size;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        CustomArray2 customArray = new CustomArray2(5);
        customArray.add(10);
        customArray.add(20);
        customArray.add(30);
        customArray.print(); // Output: 10 20 30

        customArray.remove(1);
        customArray.print(); // Output: 10 30

        System.out.println(customArray.get(1)); // Output: 30
        System.out.println(customArray.size()); // Output: 2
    }
}
