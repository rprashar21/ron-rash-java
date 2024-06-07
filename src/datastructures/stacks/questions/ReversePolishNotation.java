package datastructures.stacks.questions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ReversePolishNotation {

    public static void main(String[] args) {
     String[] tokens  = {"10","6","9","3","+","-10","*","/","*","17","+","5","+"};

        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {

        // loop thru this and push the numbers
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                if (stack.isEmpty()) {
                    continue;
                } else {
                    int value1 = stack.pop();
                    int value2 = stack.pop();
                    int value = 0;
                    switch (s) {
                        case "+":
                            value = value2 + value1;
                            break;
                        case "-":
                            value = value2 - value1;
                            break;
                        case "*":
                            value = value2 * value1;
                            break;
                        default:
                            value = value2 / value1;
                            break;
                    }
                    stack.push(value);
                    continue;
                }
            }
            stack.push(Integer.parseInt(s));
        }

        return stack.pop();
    }
}
