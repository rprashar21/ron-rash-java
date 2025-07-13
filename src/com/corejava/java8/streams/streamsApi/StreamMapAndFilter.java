package com.corejava.java8.streams.streamsApi;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.corejava.corejava.equalsandhascodes.Student;
import com.corejava.java8.streams.StudentUtility;

public class StreamMapAndFilter {


    public static void main(String[] args) {
//        getHighestMarks();
//        // Get the marks of that student whose name is bob
//        getMarks();

        // since students have hobbies which is again a list we need to flatten that list
        Set<String> collect = StudentUtility.getStudentList().stream().filter(Objects::nonNull)
                .filter(student -> student.getHobbies() !=null)
                .flatMap(student -> student.getHobbies().stream())
                .map(String::strip).collect(Collectors.toSet());
        System.out.print(collect);
        StudentUtility.getStudentList().stream().filter(Objects::nonNull).map(Student::getMarks).forEach(System.out::println);
        StudentUtility.getStudentList().stream().filter(Objects::nonNull).
                forEach(s-> System.out.println(s.getName()+" "+s.getMarks()));
        System.out.println();
        Optional<Integer> max = StudentUtility.getStudentList().stream().filter(Objects::nonNull)
                .map(Student::getMarks).max(Comparator.naturalOrder());
        System.out.println(max.get());
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
