package datastructures.recursion;

public class RecursionSample {

    // rescursion is a method where the solution to a bigger problem depends upon smaller instances of the same problem
    // we have to define a base condition to avoid infinite loops
    // every problem can be solved using iteration or recursion
    // recursion is a function calling itself
    // uses space
    // space complexity is not constant because every function call takes up memory and they are recursive

    //| Condition                                                    | Description                                              | Example                                      |
    //| ------------------------------------------------------------ | -------------------------------------------------------- | -------------------------------------------- |
    //| **1. Can I break the problem into smaller subproblems?**     | Can I solve the whole by solving parts of the same type? | Factorial, Fibonacci                         |
    //| **2. Is there a base case?**                                 | A condition when I can stop recursing?                   | `n == 0`, return 1                           |
    //| **3. Does the problem involve making choices at each step?** | Useful in **backtracking** or **DFS**                    | Generating combinations, permutations, paths |
    //| **4. Is the structure naturally recursive?**                 | Like trees, graphs, nested structures                    | Tree traversals, parsing JSON                |
    //| **5. Do I need to explore multiple paths or states?**        | Especially when you don’t know which one is correct      | Maze solving, Word Break                     |
    //| **6. Can I divide and merge results?**                       | Like Divide & Conquer                                    | Merge Sort, Quick Sort                       |

    //
    public static void main(String[] args) {
        int n=5;
        int ans = fib(n);
        System.out.println("fibonacci sum is " +ans);

        // need to find a factorial
        int factorial = fact(n);
        System.out.println(factorial);
    }

    private static int fact(final int n) {

        if(n==1)
        return n;

        return n* fact(n-1);
    }

    private static int fib(int n) {
        // base condition
        if (n < 2)
            return n;

        return fib(n - 1) + fib(n - 2);

    }
}
