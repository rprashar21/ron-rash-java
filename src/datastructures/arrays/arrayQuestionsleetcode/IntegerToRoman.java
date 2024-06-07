package datastructures.arrays.arrayQuestionsleetcode;

import java.util.Random;

public class IntegerToRoman {

    public static void main(String[] args) {

        int number = 1994;// MCMXCIV -->
        intToRoman(number);
    }

    private static String intToRoman(int num) {
//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000

//        I can be placed before V (5) and X (10) to make 4 and 9.
//        X can be placed before L (50) and C (100) to make 40 and 90.
//        C can be placed before D (500) and M (1000) to make 400 and 900.

        // Define values and symbols in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        // StringBuilder to build the Roman numeral string
        StringBuilder sb = new StringBuilder();

        // Iterate through each value-symbol pair
        for (int i = 0; i < values.length && num > 0; i++) {
            // While the current value fits into num
            while (num >= values[i]) {
                num =num - values[i]; // Subtract the value from num
                sb.append(symbols[i]); // Append the symbol to the result
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static String findRomanValues(final int num) {

        String srandom = "abcde";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < srandom.length(); i++) {
            int randomIndex = random.nextInt(srandom.length());
            stringBuilder.append(srandom.charAt(randomIndex));
        }


        return stringBuilder.toString();
    }
}
