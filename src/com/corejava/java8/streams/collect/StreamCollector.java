package com.corejava.java8.streams.collect;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;

import com.corejava.corejava.equalsandhascodes.Student;
import com.corejava.java8.streams.StudentUtility;

public class StreamCollector {
    public static void main(String[] args) {

        // counting
        // count the number of students playing chess
//        final long cricket = StudentUtility.getStudentList()
//                .stream()
//                .map(Student::getHobbies) // Stream<List<Strin>>
//                .flatMap(list -> list.stream())
//                .filter(s -> s.equalsIgnoreCase("chess"))
//                .count();
//
//        System.out.println(cricket);

        /// count the  of stude who play chess
        List<Student> studentList =StudentUtility.getStudentList();

        List<Student> collect1 = studentList.stream()
                .filter(Objects::nonNull)
                .collect(toList());

        List<String> collect2 = collect1.stream()
                .filter(student -> student.getHobbies()!=null)
                .flatMap(student -> student.getHobbies().stream())

                .collect(toList());


        long chess = collect2.stream()
                .filter(hobby -> hobby.equalsIgnoreCase("chess"))
                .count();

        System.out.println(chess);

        // mapping function similar to map

        final List<String> namesList =
                StudentUtility.getStudentList()

                .stream()
                        .filter(Objects::nonNull)
                .collect(mapping(Student::getName, toList()));


        final String collect = namesList.stream()
                .collect(joining("--"));

        System.out.println(collect);

     //

    }
}
