package datastructures.queues;

import java.util.Arrays;

public class CustomArray<T> {

    private T[] array;

    private static final Integer DEFAULT_SIZE =3;

    public CustomArray() {
        this.array = (T[])new Object[DEFAULT_SIZE];
    }

    private int count =0; // to count the elements in the array
    public void add(T data)
    {
        if(!isFull())
        {
           array[count]=data;

        }
        else {
            resizeTheArray();
            array[count]=data;
        }
        count++;
        // add the element to the array
    }

    public void remove(int index)
    {
      if(index< 0 || index >= count)
          throw new IndexOutOfBoundsException();
        else {
            for(int i=index;i<count-1;i++)
            {
                array[i]=array[i+1];
            }
      }
        count--;

    }

    private void resizeTheArray() {
//        T [] tempArray = (T[]) new Object[array.length*2];
//        for(int i=0;i< array.length;i++)
//        {
//            // copy elemnts from one array to another
//            // i can also use the copy me
//            tempArray[i]=array[i];
//        }
      T[]  tempArray = Arrays.copyOf(array,array.length*2);
        System.out.println("original"+array.length);
        System.out.println("temp"+tempArray.length);
        array=tempArray;
        System.out.println(array.length);
    }

    private boolean isFull() {
        return count==array.length;
    }
    //


    public static void main(String[] args) {
        CustomArray<Integer> integerCustomArray = new CustomArray<>();
        for(int i=1;i<=6;i++)
        {
            integerCustomArray.add(i*10);
        }
        System.out.println(integerCustomArray);
        integerCustomArray.remove(2);
        System.out.println(integerCustomArray);
        //   0,1,  2, 3, 4, 5
        // [10,20,30,40,50,60]
    }
}
