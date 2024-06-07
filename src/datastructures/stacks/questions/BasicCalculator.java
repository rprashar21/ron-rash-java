package datastructures.stacks.questions;

import java.util.Stack;

public class BasicCalculator {

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println("Result: " + calculate(s));
    }
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        int result = 0; // For the current result
        int sign = 1;  // 1 means addition, -1 means subtraction

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                operand = 10 * operand + (int) (ch - '0');
            } else if (ch == '+') {
                result += sign * operand;
                sign = 1;
                operand = 0;
            } else if (ch == '-') {
                result += sign * operand;
                sign = -1;
                operand = 0;
            } else if (ch == '(') {
                // Push the result and the sign onto the stack, for later
                // Add the sign and the current result (which could be 0)
                stack.push(result);
                stack.push(sign);
                // Reset the sign and result for the new sub-expression
                sign = 1;
                result = 0;
            } else if (ch == ')') {
                // End of sub-expression; add the sub-expression's result to the result before the parenthesis
                result += sign * operand;
                result *= stack.pop(); // stack.pop() is the sign before the parenthesis
                result += stack.pop(); // Add to the result before the parenthesis
                operand = 0;
            }
        }
        return result + (sign * operand);
    }

}
