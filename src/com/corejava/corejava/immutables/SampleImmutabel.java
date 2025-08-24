package com.corejava.corejava.immutables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SampleImmutabel
{

    // feilds shoudl be private {private scope} and final becozonce the intialized the value cannot be changed
    // make sure nt return the original refernces for mutable obhects inside an Immutable object
    // no setters only getters
   // Caching and Memoization: Immutable objects are useful in caching and memoization scenarios,
    // where you want to store computed values without the risk of them being altered
    //Enums: Enums in Java are inherently immutable.
    //String ,Wrappers classes when you change the value of the Wrapper classes it creates new objects hence the wrapper classes are always immutable.
    //such as Collections.unmodifiableList(), Collections.unmodifiableSet(), and Collections.unmodifiableMap().
    // These collections prevent modifications after creation,

    //import lombok.Value;
    //
    //@Value
    //public class UserDto {
    //    String name;
    //    String email;
    //}

    // here the lombok will generate a constructor and intialize the final fields , no setters , no getters , equals hashcode and tostring  method
    // Once created, UserDto cannot be modified.


    // using java 17 record
    // much cleaner this record is immutable by default and comes with equals hashcode and to srting
    //public record UserDto(String name, String email) {}

    // when to use in real prjects
    // use them in API responses, kafka events, domain value types (money range dimensions )

    //@Entity
    //public class Order {
    //    @Id @GeneratedValue
    //    private Long id;
    //
    //    private String product;
    //    private int quantity;
    //
    //    public Order() {} // Required by JPA
    //
    //    public Order(String product, int quantity) {
    //        this.product = product;
    //        this.quantity = quantity;
    //    }
    //
    //    // setters and getters
    //} this class is mutbale JPA needs a no args constructor becoz it proxies fields/methods to load lazily



    public static void main(String[] args) {
        List<String> mutbaleList = new ArrayList<>();
        mutbaleList.add("apples");

        System.out.println(mutbaleList);

        List<String> stringList = Collections.unmodifiableList(mutbaleList);
        mutbaleList.add("banana"); // the original list is still mutbale ;
        System.out.println(mutbaleList);
        System.out.println(mutbaleList);
        stringList.add("oranges"); // this is now a immutable list ,, this will throw an error UnsupportedException


        String originalString = "Hello, World!";
        String upperCaseString = originalString.toUpperCase();

        System.out.println(originalString); // original string remains unchanged

    }
}
