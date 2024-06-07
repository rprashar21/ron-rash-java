package com.corejava.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.corejava.corejava.equalsandhascodes.Student;

public class MapVsFlatMap {
    public static void main(String[] args) {

        // map is used to convert from one type to another and one to one mapping
        final List<String> names = StudentUtility.getStudentList().stream()
                .filter(student -> student.getName().startsWith("N")) //Stream<Student>
                .map(student->student.getName())
                .distinct().collect(Collectors.toList());


        final Stream<Student> stream = StudentUtility.getStudentList().stream();
        stream.collect(Collectors.toList());
        final Stream<Student> stream1 = StudentUtility.getStudentList().stream();
        stream1.collect(Collectors.toList());
        //flatmap is used for transformation + flattening the stream -- one to many mapping
        // processes a stream of stream of values
        // this is a mapper function which produces multiple values for each input
        final List<String> hobbies = StudentUtility.getStudentList().stream()
                .flatMap(student -> student.getHobbies().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(hobbies);

        final List<Student> collect = StudentUtility.getStudentList(); // collection
        final List<String> namesList= new ArrayList<>();
//        for(Student student:collect)
//        {
//            if(student.getName().startsWith("N"))
//            namesList.add( student.getName());
//        }

    }
}
