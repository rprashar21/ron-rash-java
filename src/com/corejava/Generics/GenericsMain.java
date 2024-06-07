package com.corejava.Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.corejava.Generics.methods.Building;
import com.corejava.Generics.methods.House;
import com.corejava.corejava.equalsandhascodes.Person;

public class GenericsMain {


    /// we can create a generics method as well which can take any value
    private static <T> void print(T value){
        System.out.println(value);
    }

    public static <T> Integer takeAnyType(T type)
    {
        // service service.callexternalSevce(type)
        System.out.println(type);
        return 1;
    }
    public static <T extends Number,U> Integer Unbounded(T type,U anyType)
    {
        // service service.callexternalSevce(type)
        System.out.println(type);
        System.out.println(anyType);
        return 1;
    }
    private static List<?> takesAList(List<? extends Building> anyList)
    {
        System.out.println(anyList);

        return anyList;
    }

    // as many params can be passed
    private static <T,U,A> void print (T t,U u,A a){
        System.out.println(t+" "+u+""+a);
    }

   private static void print(List<? extends Number> list)
   {
       System.out.println(list.get(0));
   }

    public static void main(String[] args) {



        GenericsMain.takesAList(Arrays.asList(new House("green")));
       GenericsMain.takeAnyType("hello owrld");
        GenericsMain.takeAnyType(new Person(1,"rohit"));
        GenericsMain.takeAnyType(120);

        GenericsMain.Unbounded(12,new Person(2,"swati"));
        //
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new String("abc"));
        list.add(1);

        final Object o = list.get(2);
        System.out.println("generics "+o);

        SampleGenerics<Integer> sampleGenerics = new SampleGenerics<>(19);
        // if generics extends NUmber then we can only pass whatever is child of inherits Number
        // SampleGenerics<String> stringSampleGenerics= new SampleGenerics<>("rohit");

        sampleGenerics.print();
        //  stringSampleGenerics.print();

        print(1);
        print("Hello world");
        print(new SampleGenerics<Integer>(100));
        List<Integer> integers = new ArrayList<>();
        print(1,"hello ",new SampleGenerics<Integer>(100));
        integers.add(1090);
        print(integers);
    }
}
