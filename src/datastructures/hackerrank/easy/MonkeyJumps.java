package datastructures.hackerrank.easy;

public class MonkeyJumps {
    public static void main(String[] args) {
        String result = kangaroo(43, 2, 70, 2);
        System.out.println(result);
    }

    public static String kangaroo(int x1, int v1, int x2, int v2) {
        // Write your code here
        //Assume x1  is starting distance
        // x2 is starting distance
        // formula is distance = speed *time -- time here is no of jumps
        // jumps = x1 - x2/ v2- v1 if the remainder is o then no of jumps are equal and hence they will meet at apoint
        if (v2 >= v1)
            return "NO";
        int mod = (x2 - x1) % (v1 - v2);
        if (mod == 0)
            return "YES";
        else
            return "NO";
    }
}
