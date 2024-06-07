package datastructures.arrays;

public class App {

    public static void main(String[] args) {
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>();
        customArrayList.add(10);
        customArrayList.add(20);
        customArrayList.add(30);
        customArrayList.add(40);

        customArrayList.print(); // 10 20 30 40
        customArrayList.remove(4);
        customArrayList.print(); // 10 20 30
        System.out.println("value :: "+customArrayList.get(2)); // 30

        customArrayList.set(0,900);
        customArrayList.print(); // 900 20 30
    }

}
class CustomArrayList<T>{
    private Object[] array;
    private static Integer DEFAULT_SIZE = 3;
    private int size;

    public CustomArrayList() {
        this.array = new Object[DEFAULT_SIZE];
    }

    void add(T data) {
        // check if the array is full
        // if yes resize or else add the value
        if (isArrayFull()) {
            resize();
        }
        array[size++] = data;
    }
    public Object get(int index) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void remove(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }
    private boolean isArrayFull() {
        return array.length == size;
    }

    private void resize() {
        // create a temporary array with double the size ,,copy the items in that
        Object[] temp = new Object[array.length * 2];

        // Start copying th elements from original array into temp
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void set(int index,Object value)
    {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        array[index]= value;
    }

    public void print(){
        System.out.println();
        for(int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }
}