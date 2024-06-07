package datastructures.stacks.implementation;

public class mainAp {

    public static void main(String[] args) {

        StackCustomLinkedList<String> stack = new StackCustomLinkedList<>();
        stack.push("hello");
        stack.push("hi");
        stack.push("go");

        // lifo

        while(!stack.isEmpty())
        {
            System.out.println(stack.pop());
        }
    }
}
