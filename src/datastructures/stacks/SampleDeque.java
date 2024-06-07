package datastructures.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class SampleDeque {

//
//    Comparison of Stack and Deque methods
//    Stack Method	Equivalent Deque Method
//    push(e)	addFirst(e)
//    pop()	removeFirst()
//    peek()	peekFirst()

 //   https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html

    public static void main(String[] args) {

//        The line of code you provided, transient Object[] elements;,
//        declares a transient instance variable named elements of type Object[] in a class.
//
//The transient keyword in Java is used to indicate that a field should not be serialized when an object
// of the class is being serialized. During the serialization process,
// transient fields are ignored, and their values are not persisted.
// When the object is deserialized, transient fields are set to their default values (e.g., null for reference types).
//
//In the context of the line you provided, the elements array is marked as transient,
// which means that when an object of this class is serialized, the elements array will not be included in the serialized form. This can be useful in cases where the array contains temporary or non-essential data that doesn't need to be preserved when the object is serialized and later deserialized.
//
//Note that the transient keyword can only be applied to instance variables (fields) and not to local variables or methods.
        Deque<Integer> stackDeque = new ArrayDeque<>();
        stackDeque.addFirst(10);  // [30,20,10]
        stackDeque.addFirst(20);
        stackDeque.addFirst(30);

        System.out.println("peek "+ stackDeque.peekFirst());
        while(!stackDeque.isEmpty())
        {
            System.out.println(stackDeque.removeFirst());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);  // [30,20,10]
        stack.push(20);
        stack.push(30);

        System.out.println("peek "+ stack.peekFirst());
        while(!stack.isEmpty())
        {
            System.out.println(stack.pop());
        }


      //  []
    }

}
