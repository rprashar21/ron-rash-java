package com.corejava.java8.streams.practice;


import java.util.List;

public class Practice {
    public static void main(String[] args) {

        // immutable list ,does not allow adding,delete and null values
        final List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6,8,10);



        // find square of the first 3 evern numbers
         integerList.stream().filter(elem-> elem%2==0)
                 .limit(3)
                 .map(elem->elem*elem)
                 .forEach(System.out::println);

    }
    // stream has intermediate and terminal operations
    // intermediate --> filter,map,flatmap,limit,distinct,sorted,skip,peek
   // terminal operations --> collect,foreach,count,min,max,average,finfirst,findany,anyMatch,matchnone

    // Streams are lazy unless a terminal operation is recieved

    // map -- intermediate --> takes data and transforms into another -- basically takes in a function <T,R>


}
