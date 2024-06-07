package com.corejava.java8.streams;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.corejava.corejava.equalsandhascodes.Student;

public class IntermediateOperations {

    public static void main(String[] args) {

        // map and flatmap
        // they return a stream as output
        // map is used for transformatin
        // flatmap is used for transformation and flattening

        // map function  produces a single value for each input value
        // one to one mapping

        // flat map -- converting stream of stream into a single stream

        // db call
        final List<Student> studentList = StudentUtility.getStudentList();
     //S1 -{name,age,marks,department,hobbies{swim,gymin,football}}
     //S2 -{name,age,hobbies{boxing,gymin,football}}
     //S3 -
        // I want all the hobiies and students have a ist of hobbies

        Predicate<? super Student> predicate = student -> student.getMarks() > 95;

        final List<String> collect = studentList.stream() // s1,s2,s3,s4
//                .filter(student -> student.getMarks()>95)
                .filter(studentfilter->studentfilter.getMarks()>95)// s2,s3
                .filter(predicate)
                .flatMap(student -> student.getHobbies().stream())
                //    .distinct()
                .sorted()
                .collect(Collectors.toList());


        System.out.println(collect);
//
//        However, when using a collection like HashSet that relies on both equals and hashCode
//        methods for determining equality and ensuring that there are no duplicates, you also need to override the hashCode method.
    }
}
