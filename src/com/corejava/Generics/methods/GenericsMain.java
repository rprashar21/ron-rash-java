package com.corejava.Generics.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenericsMain {

    /*
     *   generics were added to ensure type safety
     *  And to ensure that generics won't cause overhead at runtime, the compiler applies a process called type erasure on generics at compile time.
     * Type erasure removes all type parameters and replaces them with their bounds or with Object if the type parameter is unbounded.
     * This way, the bytecode after compilation contains only normal classes, interfaces and methods, ensuring that no new types are produced.
     * Proper casting is applied as well to the Object type at compile time.
     *
     *
     * */

    // ProductCatalog compilation
    public <T> List<T> genericMethod(T[] list) {
        return new ArrayList<>();
    }
    // after compilation type erasure kicks in

    public List<Object> genericMethodAfterCompilation(Object[] array){
        return new ArrayList<>();
    }

    public List<Object> genericMethod(List<Object> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    // ProductCatalog compile
    public <T extends Building> void genericMethod1(T t) {
        System.out.println(t);

    }

    // after compilation type erasure kicks in
    public void genericMethod(Building building) {
   /*
   *  type parameters must be convertible to Object. Since primitive types don't extend Object, we can't use them as type parameters.

     However, Java provides boxed types for primitives, along with autoboxing and unboxing to unwrap them:
  *
  * */

    }

//    List<Integer> list = new ArrayList<>();
//        list.add(17);
//    int first = list.get(0);

        // comiled code is this

//    List list = new ArrayList<>();
//list.add(Integer.valueOf(17));
//    int first = ((Integer) list.get(0)).intValue();
}
