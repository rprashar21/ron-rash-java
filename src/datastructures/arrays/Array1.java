package datastructures.arrays;

import java.util.Arrays;

public class Array1 {
    public static void main(String[] args) {

        // An array in Java is a conatiner  object that holds a fixed number of values of a single type.

        int[] array = new int[]{1, 2, 3, 4, 5}; // declaration and intialization
        Person person = new Person("rohit");
        Department department = new Department("Computer Science");
        Object[] arrayOfObjects = new Object[]{person, department};

        for (Object object : arrayOfObjects) {
            if (object instanceof Person) {
                Person person1 = (Person) object;
                System.out.println(person1.getName());
            } else {
                Department department1 = (Department) object;
                System.out.println(department1.getDepartName());
            }
        }


        int[] arrr = new int[10]; //declaration
        String[] names = {"rohit", "swati"};
        for (int i = 1; i <= 10; i++) {
            arrr[i - 1] = i * 10;
        }
        System.out.println(Arrays.toString(arrr));
        System.out.println(Arrays.toString(names));

        for (int i : array) { // enhanced for loop
            System.out.print(i);
        }
        // we can use
        // converts an array to string
        // display ecah element in the
        final String s = Arrays.toString(array);
        Array1.customString(array);
        System.out.println(s);


        // if there is a 2d ARRAY
        System.out.println("printing 2d array");
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9, 10}
        };
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }

        // array of objects
        String[] strings = new String[]{"a", "b"};
        System.out.println(Arrays.toString(strings));

    }

    private static void customString(final int[] array) {
        System.out.println("inside custom array");
        for (int i : array) {
            System.out.print(String.valueOf(i) + " ");
        }
    }


}

class Person {
    private String name;

    public Person(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

class Department {
    private String departName;

    public Department(final String departName) {
        this.departName = departName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(final String departName) {
        this.departName = departName;
    }
}