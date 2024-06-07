package com.corejava.java8.streams.streamsApi;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StreamsSumAverage {
    public static void main(String[] args) {

        // average

        final Double collect = Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .collect(Collectors.averagingInt(Integer::intValue));

        System.out.println(collect);


        // sum of all numbers

        final int sum = Arrays.asList(1, 2, 3).stream().collect(Collectors.summingInt(i -> i)).intValue();
        System.out.println(sum);

//        System.out.println(Arrays.asList(1, 2, 3, 4)
//                .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
//        List<Student> studentList = StudentUtility.getStudentList();
//        Collections.sort(studentList, new myComparator());
//        System.out.println(studentList);
//
//        // usng streanss
//        final Map<String, List<Student>> collect = studentList.stream()
//                .sorted(Comparator.comparing(s -> {
//                    return s.getAge();
//                }))
//                .collect(Collectors.groupingBy(Student::getName, Collectors.toList()));
//
//        System.out.println(collect);

    }
}
