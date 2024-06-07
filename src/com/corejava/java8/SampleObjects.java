package com.corejava.java8;

import java.util.Objects;

public class SampleObjects {
    public static void main(String[] args) {
        Student student=null;

        processDetails(student);
    }

    private static void processDetails(final Student student) {
        // This class consists of static utility methods for operating on objects
        Objects.requireNonNull(student,"student object cannot be null");
        System.out.println(student.name);
    }
}

class Student{

    String name;

    public Student(final String name) {
        this.name = name;
    }
}
