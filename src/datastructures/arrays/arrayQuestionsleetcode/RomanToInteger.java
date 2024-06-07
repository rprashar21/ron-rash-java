package datastructures.arrays.arrayQuestionsleetcode;

import java.util.HashMap;

public class RomanToInteger {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        romanToInteger(s);
        romanToIntegerSimple(s);
    }

    private static int romanToInteger(final String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

//        I can be placed before V (5) and X (10) to make 4 and 9. IV = 5 IX =9
//        X can be placed before L (50) and C (100) to make 40 and 90. XL = 40 and XC =90
//        C can be placed before D (500) and M (1000) to make 400 and 900. CD=400 and CM =900

        // String s = "MCMXCIV"; 1000+900+90+4 =2994
        int sum = map.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum = sum - map.get(s.charAt(i));

            } else {
                sum = sum + map.get(s.charAt(i));
            }
        }
        System.out.println(sum);
        return sum;
    }

    private static int romanToIntegerSimple(final String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

//        I can be placed before V (5) and X (10) to make 4 and 9. IV = 5 IX =9
//        X can be placed before L (50) and C (100) to make 40 and 90. XL = 40 and XC =90
//        C can be placed before D (500) and M (1000) to make 400 and 900. CD=400 and CM =900

        // iterate from left to right
        int totalSum = 0;
        int prevValue = 0;
//        String s = "MCMXCIV";
        for (int i = s.length() - 1; i >= 0; i--) {
            // 5
            int currentValue = map.get(s.charAt(i));
            if (currentValue > prevValue) {
                totalSum = totalSum + currentValue;
            } else {
                totalSum = totalSum - currentValue;
            }
            prevValue = currentValue;
        }
        System.out.println(totalSum);
        return totalSum;
    }


}
