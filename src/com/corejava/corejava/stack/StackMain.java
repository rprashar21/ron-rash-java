package com.corejava.corejava.stack;

import java.util.Stack;

public class StackMain {
    public static void main(String[] args) {

        int[] a = {6,8,3,10};

        Stack<Integer> stack = new Stack<>();

        for(int i=a.length-1;i>=0;i--)
        {
            stack.push(a[i]);
        }
        System.out.println(stack);
        for(int i=0;i<a.length-1;i++)
        {
            final Integer peek = stack.peek();
            if(peek<a[i+1])
            {
                stack.pop();
            }


        }

    }
}
