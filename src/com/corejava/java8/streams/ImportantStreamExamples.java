package com.corejava.java8.streams;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.corejava.corejava.equalsandhascodes.Student;

public class ImportantStreamExamples {

    public static void main(String[] args) {
        List<String> postcodes = getPostcodes();
        
        // list of string hai ,, we need to have , in between then best is 


        String collect = postcodes.stream().collect(Collectors.joining(","));
        System.out.println(collect);

        // I have a list  of postcode and need to match with my postcodes

        List<String> mypostcodes = Arrays.asList("CR0 1HB","CR0 5UA","SE2 2PH","BR1 2HS");
    }
//        return getResultedHearings()
//                .map(Hearings::getDefendants) // List<DefendantOBjects >
//                .filter(Objects::nonNull) // checks for any object null
//                .flatMap(Collection::stream) // again maikea a collection out of it 
//                .filter(d -> defendantId.equals(d.getId()))
//                .map(Defendants::getJudicialResults)
//                .filter(Objects::nonNull)
//                .flatMap(Collection::stream)
//                .collect(toList());
//    }

    public static List<String> getPostcodes() {
//       return StudentUtility.getStudentList().stream()
//               .map(Student::getAddress)
//               .filter(Objects::nonNull)
//               .flatMap(Collection::stream)
//               .map(Address::getPostcode)
//               .filter(Objects::nonNull)
//               .collect(Collectors.toList());

//        return StudentUtility.getStudentList()
//                .stream()
//                .filter(Objects::nonNull)
//                .flatMap(student -> student.getAddress().stream())
//                .filter(Objects::nonNull)
//                .peek(address -> System.out.println(address.getPostcode()))
//                .map(Address::getPostcode)
//                .collect(toList());

//        List<Address> collect = StudentUtility.getStudentList()
//                .stream()
//                .filter(Objects::nonNull)
//                .flatMap(student -> student.getAddress().stream())
//                .filter(Objects::nonNull)
//                .peek(address -> System.out.println(address.getPostcode()))
//                .collect(toList());


        List<Student> studentList = StudentUtility.getStudentList();
//        Stream<Student> studentStream = studentList.stream().filter(Objects::nonNull);
//        List<String> postcode = studentStream.
//                filter(student -> student.getAddress() != null)
//                .flatMap(student -> student.getAddress().stream())
//                .map(address -> address.getPostcode())
//                .collect(toList());

// list of student objects ,, we need to get the postcodes matching CR0
        List<String> postcodes = studentList.stream()
                .filter(student -> student.getAddress() != null) // this has just filtered out the students whose address is not null
                .flatMap(student -> student.getAddress().stream())
                .map(address -> address.getPostcode())
                .filter(add -> add.startsWith("CR0"))
                .collect(toList());


        return postcodes;
               // students can be null
    }
}
