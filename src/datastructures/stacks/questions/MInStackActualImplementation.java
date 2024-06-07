package datastructures.stacks.questions;

import java.util.Stack;

public class MInStackActualImplementation {

    // need to find  the min or max elemnt in constant time
    // have two stacks one stack will have al the elemnets pushed and other will have the minimum stack

    public static void main(String[] args) {
        MinStacks minStacks = new MinStacks();
         minStacks.push(50);
        minStacks.push(20);
        minStacks.push(2);
        minStacks.push(3);

        System.out.println(minStacks.getMin());
        minStacks.pop();
        minStacks.pop();
        System.out.println(minStacks.top());
        System.out.println(minStacks.getMin());
    }

}

class MinStacks {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int value) {
        stack.push(value);

        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("stack is empty insert some values first");
        }
        return minStack.peek();
    }

    public int top() {
        return stack.peek();
    }

    public void pop() {
        int popped = stack.pop();
        if (popped == minStack.peek()) {
            minStack.pop();
        }
    }
}