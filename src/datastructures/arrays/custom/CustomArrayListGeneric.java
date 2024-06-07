package datastructures.arrays.custom;

public class CustomArrayListGeneric<T>{
    //T is the generic type parameter that represents the type of element we put in the array
    private Object[] array;
    private int size;

    public CustomArrayListGeneric() {
      this.array = new Object[3];// default size of 3
        this.size=0;
    }

    public void add(T data)
    {
        if(size==array.length)
        {
            // resize the array
            Object[] tempArray = new Object[array.length*2];
            // copy the elemnts
            System.arraycopy(array, 0, tempArray, 0, size);
            array=tempArray;
        }
        array[size]=data;
        size++;
    }

    public void remove(int index)
    {
        if(index <0 || index >=size)
            throw new IndexOutOfBoundsException("index out of range");

        //
        for(int i=index;i<size-1;i++)
        {
            array[i]=array[i+1];
        }
        size--;
    }

    public void set(int index ,T data)
    {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        array[index]=data;
    }

    public T get(int index ) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return (T) array[index];
    }
    public void print (){

        for(int i=0;i<size;i++)
        {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        CustomArrayListGeneric<String> customArrayListGeneric = new CustomArrayListGeneric<>();
        customArrayListGeneric.add("apple");
        customArrayListGeneric.add("orange");
        customArrayListGeneric.add("banana");
        customArrayListGeneric.print();
        customArrayListGeneric.get(2);
        System.out.println(  customArrayListGeneric.get(2));
        customArrayListGeneric.set(1,"cucumber");
        customArrayListGeneric.print();

    }
}
