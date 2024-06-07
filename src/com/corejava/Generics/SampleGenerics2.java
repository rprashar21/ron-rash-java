package com.corejava.Generics;

import java.util.Arrays;
import java.util.List;

public class SampleGenerics2 {
    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.set(20);
        Integer integer = box.get();
        System.out.println(integer);
        box.set(20);
        box.get();
     Integer[] array = {1,2,3};
        box.printArray(array);
    }
}

// It makes sure that you can add only specific type of objects in a collection
//Generics in Java allow you to write more general,
// safer code, avoiding the need for casting and reducing the risk of runtime errors.
class Box<T>
{
    private T data;
    public void set(T data){
       this.data = data;
    }
    public T get(){
        return this.data;
    }
    // type Parameter to be used for readability
    public <T> void printArray(T[] array)
    {
        for(T t:array)
        {
            System.out.println("hello "+ t);
        }

    }
   // Wildcard Type Arguments: Wildcards (?) are used when you want to work with multiple types,
    // but don't know which specific type will be used.
    public void processElements(List<?> list){
        System.out.println(list.get(0));
    }
    // make sure that the type passed is child of Number -- Integer float Byte short
    public void processElementsWithSoecificType(List<? extends Number> list){
        System.out.println(list.get(0));
    }
}

  //  Type Erasure: Generic type information is used for type checking at compile time,
//  but it's removed (erased) at runtime for backward compatibility.
//  This means that generic types are treated as Object during runtime.

   // Cannot Create Instances of Type Parameters: You cannot create instances of a type parameter.
// For example, new T() is not allowed because at runtime the type T is erased to Object.

// You cannot have a static field of the type parameter.
// This is because the type is not known at the class level (only at the instance level).