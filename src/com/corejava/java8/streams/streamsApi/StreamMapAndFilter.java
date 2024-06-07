package com.corejava.java8.streams.streamsApi;

import java.util.Comparator;
import java.util.Optional;

import com.corejava.corejava.equalsandhascodes.Student;
import com.corejava.java8.streams.StudentUtility;

public class StreamMapAndFilter {


    public static void main(String[] args) {
        getHighestMarks();
        // Get the marks of that student whose name is bob
        getMarks();
    }

    private static void getMarks() {
        final int marks = StudentUtility.getStudentList()
                .stream()
                .filter(s -> s.getName().equalsIgnoreCase("Bob"))
                .findAny().get().getMarks();

        System.out.println(marks);
    }

    private static Student getHighestMarks() {
        final Optional<Student> studentWithHighestMarks = StudentUtility.getStudentList().stream()
                .reduce((student, student2) ->
                {
                    if (student.getMarks() > student2.getMarks())
                        return student;
                        return student2;
                });

        final Optional<Student> student = StudentUtility.getStudentList()
                .stream()
                .max(Comparator.comparing(Student::getMarks));
        System.out.println(student.get().getName() + " " + student.get().getMarks());
        return studentWithHighestMarks.get();
    }
}
