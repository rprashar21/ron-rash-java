package datastructures.stacks.hacckerank;

import java.util.Stack;

public class ValidParanthesis {

    public static void main(String[] args) {
        String s = "{}";
        // System.out.println(isValid(s));
        ;

        System.out.println(isValidNew(s));

        // // insert the opening in the  stack and remove the closing from the sack
    }

    private static boolean isValidNew(final String s) {
        var stack = new Stack<Character>();
        for (Character ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || !matches(stack.peek(), ch)) {
                    return false;
                } else {
                    // Pop the matched opening bracket from the stack
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    }
}
