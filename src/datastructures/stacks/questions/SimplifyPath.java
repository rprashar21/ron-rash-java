package datastructures.stacks.questions;

import java.util.Arrays;
import java.util.Stack;

public class SimplifyPath {
    public static void main(String[] args) {
        String path = "//foo";
        System.out.println("Simplified path: " + simplifyPath(path));

        // split the string based on /
        // loop thru the stringa raay and check if .. is there then remove the last elemnt from the stack
        // if "." or "" then continyue else enter  the elements on the stack
        // create a builder and starting appendin starting with "/"
        // if string is emplt  theb return "/" else keep it empty
    }

    private static String simplifyPath(final String path) {

        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");

        for (String directory : components) {
            // If directory is ".." and stack is not empty, pop the stack
            if (directory.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            // Ignore "." and empty string (which is a result of consecutive slashes)
            else if (!directory.equals(".") && !directory.isEmpty()) {
                stack.push(directory);
            }
        }

        // Rebuild the simplified path
        StringBuilder simplifiedPath = new StringBuilder();
        for (String dir : stack) {
            simplifiedPath.append("/").append(dir);
        }

        // Handle the case of the simplified path being empty
        return simplifiedPath.length() == 0 ? "/" : simplifiedPath.toString();
    }
}
