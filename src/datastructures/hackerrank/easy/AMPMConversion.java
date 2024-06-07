package datastructures.hackerrank.easy;

import java.util.HashMap;
import java.util.Map;

public class AMPMConversion {

    public static void main(String[] args) {

        String s ="04:59:59PM";

        System.out.println(timeConversion(s));

    }
    public static String timeConversion(String s) {
        // Write your code here
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,13);
        map.put(2,14);
        map.put(3,15);
        map.put(4,16);
        map.put(5,17);
        map.put(6,18);
        map.put(7,19);
        map.put(8,20);
        map.put(9,21);
        map.put(10,21);
        map.put(11,23);
        map.put(12,12);

      String checkTimeFormat = s.substring(8,10);
      String tocheck = s.substring(0,2);
      String result = s.substring(2,s.length()-2);

        if(checkTimeFormat.equalsIgnoreCase("PM") && Integer.parseInt(tocheck)<=12)
      {
         return String.valueOf(map.get(Integer.parseInt(tocheck))).concat(result);
      }
      else if(tocheck.equalsIgnoreCase("12"))
        {
          return "00".concat(result);
        }

      else
          return s.substring(0,s.length()-2);
    }
}
