package datastructures.recursion;

public class RecursionPrint {
    public static void main(String[] args) {

       printRecursion(5);
        printRecursionReverse(5);
    }

    private static void printRecursion(final int n) {

        if(n==0){

            return;
        }
        System.out.println(n);

        printRecursion(n-1);
    }

    // print in reverse
    private static void printRecursionReverse(final int n) {

        if(n==0){

            return;
        }
        printRecursionReverse(n-1);
        System.out.println(n);
    }
}
