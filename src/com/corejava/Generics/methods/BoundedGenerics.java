package com.corejava.Generics.methods;

public class BoundedGenerics {
    /*
    * Type parameters they can be bounded , we can restrict the type a method accepts
    * For example, we can specify that a method accepts a type and all its subclasses (upper bound)
    * or a type and all its superclasses (lower bound).
    * To declare an upper-bounded type, we use the keyword extends after the type, followed by the upper bound that we want to use:
    * A type can also have multiple upper bounds:
    *
    * */

    private static <T extends Number> T getMultipliedValue(T someType)
    {
        return someType;

    }

    public static void main(String[] args) {
        // passing a value which extends Number
        final Integer multipliedValue = BoundedGenerics.getMultipliedValue(10);
        // passinga long value also extends Number 
        final Long multipliedValue1 = BoundedGenerics.getMultipliedValue(10L);
    }
}
 class BoundedGeneric2<T> {

    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        BoundedGeneric2<Integer> integerBox = new BoundedGeneric2<Integer>();
        integerBox.set(new Integer(10));
        // in this method we cannot pass a string becoz it is bounded
        integerBox.inspect(2); // error: this is still String!
    }
}