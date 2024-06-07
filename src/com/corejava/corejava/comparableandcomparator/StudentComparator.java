package com.corejava.corejava.comparableandcomparator;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(final Student student1, final Student student2) {

        return -student1.getName().compareTo(student2.getName());
    }
}
