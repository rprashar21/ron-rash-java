package collections.exampless;

import java.util.Arrays;

public class SampleProgram {

    public static String title ="acharya";
    public String name="swati";
    public int[] reverseArray(int[] array) {

        int[] outputArray = new int[array.length];
        int k = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            outputArray[k] = array[i];
        }
        return outputArray;
    }

    public static void reverseString(char[] s) {

        int first = 0;
       int last = s.length-1;
        System.out.println(title);
       while(first<last){
           char temp = s[first];
           s[first] = s[last];
           s[last] = temp;
           last--;
          first++;
       }
        System.out.println(Arrays.toString(s));
        }
    }


