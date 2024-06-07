package datastructures.recursion;

public class RecursionFibonnaci {
    public static void main(String[] args) {

        int n = 4;
        fib(n);
    }

    public static int fib(int n) {
        //f(n) =f(n-1)+f(n-2)

        //0,1,1,2,3,5,8,13,21

        // we have 2 base cases f(n-1) and f(n-2)

        // we difine the base cases
        if(n==0)
            return 0;
        if(n==1)
            return 1;

        int fib1 = fib(n-1); // this will be called recursively
        int fib2 = fib(n-2); // this wil be called recursively

        // there is possibilty that the firest recursive call will resolve some of th problem ,,
        // so there might be calls which mihht have been resolved in the previous case which can be avoided using  dynamic programming

        int result= fib1 +fib2;
        return result;

    }
}
