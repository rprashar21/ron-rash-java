package datastructures.linkedlists.implementation.singleLinkedList;

import java.util.Comparator;

public class Person implements Comparable<Person> {

    private String name;
    private int marks;

    public Person(final String name, final int marks) {
        this.name = name;
        this.marks = marks;
    }



    @Override
    public int compareTo(final Person o) {
        return Comparator.comparing(Person::getName).thenComparingInt(Person::getMarks).compare(this,o);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(final int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
