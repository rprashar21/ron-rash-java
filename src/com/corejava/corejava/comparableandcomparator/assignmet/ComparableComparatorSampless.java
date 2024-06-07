package com.corejava.corejava.comparableandcomparator.assignmet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComparableComparatorSampless {
    public static void main(String[] args) {
        // Integer and String class implement Comparable interface
        List<Integer> integerList = Arrays.asList(2, 4, 5, 1);
        List<String> stringList = Arrays.asList("b", "d", "a", "c");
        System.out.println();
        Collections.sort(integerList); // o/p --> 1,2,4,5
        System.out.println(integerList);
        Collections.sort(stringList); // o/p --> a,b,c,d
        System.out.println(stringList);


        int [] array = new int[]{2,1,5,3};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));


        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Alice", 25));
        personList.add(new Person("Bob", 30));
        personList.add(new Person("Charlie", 20));
        Collections.sort(personList); //
        System.out.println(personList);

    }
}
// Sorting Custom Objects
//
class Person implements Comparable<Person> {
    private String name;
    private int age;

    // Constructors, getters, and setters // toString()


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override // comparable method sorting based on name or age
    public int compareTo(Person person) {
      //  return this.age> person.getAge()?1:-1;
        //return Integer.compare(this.age, person.age);
        return this.name.compareTo(person.name);
    }
    // Sorting the list of Person objects

}