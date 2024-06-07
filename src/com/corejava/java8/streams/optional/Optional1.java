package com.corejava.java8.streams.optional;

import java.util.Arrays;
import java.util.Optional;

import com.corejava.corejava.equalsandhascodes.Student;
import com.corejava.java8.streams.StudentUtility;

public class Optional1 {
    public static void main(String[] args) {

        // of -- when we are sure our object is not null

        // ofNullable -- when we don't know it  Optional.emreturnspty if object is null

        // orElse orElseGet orElseThrows

        // isPresent-- checks if value is present
        // ifPresent(s->sout(s)) -- checks if the value is present or not  and give additional operation to perform on that

        Student student = new Student(null,28,90, Arrays.asList(""),"CSE");

        // if we get a null value we can replace it by default value
        // we can alos use orElseThrows -- throw an exception or use a supplier interfcace

      //  final String default_name = Optional.ofNullable(student.getName()).orElse("default name");

     //   final String default_name = Optional.ofNullable(student.getName()).orElseGet(()->"default name");
                                            /// this takes in a supplier basically it
        // tells that if the student name is null u can see the default value by using orElse("default name");
        // or use orElseGet since this is a supplier to get the value from somwhere maybe call a new service or use a fallback method
        // orelse Throw an exception a runtime or a custom exception UserNameshould not be null
        Optional.ofNullable(student.getName()).orElseGet(()->{
            return "ronny";
        });
        Optional<Student> studentOptional = Optional.ofNullable(new Student(null,21,90,null,"CSe"));
        studentOptional.flatMap(student1 -> Optional.ofNullable(student1.getName())).ifPresent(studentName -> {
            // basically do something with string whicc is a name and then  to something more
        });

      //  final String default_name = Optional.ofNullable(student.getName()).orElseThrow(()->new IllegalArgumentException("email not present"));

//        System.out.println(default_name);

        // find a student with the given department
        String department="abc";
        final Optional<Student> studentWithMatchingDepartment = getStudentWithMatchingDepartment(department);
        if(studentWithMatchingDepartment.isPresent())
            studentWithMatchingDepartment.get();

//        Optional.ofNullable(getStudentWithMatchingDepartment(department)).isPresent(student1 -> System.out.println(student1.get())).;

        Optional.ofNullable(getStudentWithMatchingDepartment(department)).ifPresent(student1 -> System.out.println(student1));
        if(studentWithMatchingDepartment.isPresent())
        {

        }
    }

    private static Optional<Student>  getStudentWithMatchingDepartment(String department){

       // 1st case if the student does not match we create a new student or we can enter into the db or do whever
//      return   StudentUtility.getStudentList()
//                .stream()
//                .filter(student -> student.getDepartment().equalsIgnoreCase(department))
//                .findAny()
//                .orElse(new Student());

        // 2nd  case if the student does not match we throw an exception saying that the student is not in or department
//        return   StudentUtility.getStudentList()
//                .stream()
//                .filter(student -> student.getDepartment().equalsIgnoreCase(department))
//                .findAny()
//                .orElseThrow(()->new RuntimeException("no student with matching department"));


// 3rd case w just return teh
            return Optional.of(StudentUtility.getStudentList()
                    .stream()
                    .filter(student -> student.getDepartment().equalsIgnoreCase(department))
                    .findAny().orElseGet(() -> {
                        return new Student();
                    }));
    }
}
