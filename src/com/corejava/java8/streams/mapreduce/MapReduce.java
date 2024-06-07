package com.corejava.java8.streams.mapreduce;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.corejava.corejava.equalsandhascodes.Student;
import com.corejava.java8.streams.StudentUtility;

public class MapReduce {
    public static void main(String[] args) {

        final List<Integer> integerList = Arrays.asList(1, 8, 9, 10);

        // find sum average

       integerList.
                stream()
                .mapToInt(i -> i)
                .sum();

        OptionalDouble average1 = integerList.stream().mapToInt(Integer::intValue).average();
        System.out.println(average1.getAsDouble());



        // max or min
        integerList.stream()
                .mapToInt(i->i)
                .max();

          // average
        integerList.stream()
                .mapToInt(i->i)
                .average();

        // using reduce
        final Optional<Integer> sum = integerList.stream().reduce(Integer::sum);
        final Optional<Integer> max = integerList.stream().reduce(Integer::max);

        // find the longest string
        final List<String> stringList = Arrays.asList("abc", "ab", "abcde",null);
        final String default_string = stringList
                .stream()
                .filter(s -> s != null)
                .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2)
                .orElse("default string");

        Optional<String> reduce = Stream.of(null, "ab", "prashar", null)
                .filter(Objects::nonNull)
                .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2);

        reduce.ifPresent(System.out::println);


        System.out.println(default_string);

        final String s ="india is my country";
        final Stream<char[]> charArray = Stream.of(s.toCharArray());
        final long count = s.chars().filter(c -> c == 'i').count();
        System.out.println("count is "+count);

        List<String> strings = Arrays.asList("11","12","13","14");
        final IntStream intStream = strings.stream().mapToInt(i -> Integer.parseInt(i));

      for(int i=0;i<strings.size();i++)
      {
          Integer.parseInt(strings.get(i));
      }

        final char[] chars = s.toCharArray();
        int sumss =0;
        for (final Character ch : chars) {
            if (ch == 'i')
                sumss += 1;
        }
        System.out.println(sumss);
        // find avergae marks of studenst whose age> 30

        final Double average = StudentUtility.getStudentList()
                .stream()
                .filter(student -> student.getAge() > 30)
                .mapToDouble(Student::getMarks)
                .average().getAsDouble();

        System.out.println(average);



    }
}
