package datastructures.maths;

public class Lcm {
    public static void main(String[] args) {
        int num1 = 12, num2 = 30;
        int lcm = findLCM(num1, num2);
        System.out.println("LCM of " + num1 + " and " + num2 + " is " + lcm);
    }

    // Function to find LCM of two numbers
    public static int findLCM(int num1, int num2) {
        int gcd = findGCD(num1, num2);
        return (num1 * num2) / gcd;
    }

    // Function to find GCD of two numbers
    public static int findGCD(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return findGCD(num2, num1 % num2);
    }
}

