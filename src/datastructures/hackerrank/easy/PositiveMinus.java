package datastructures.hackerrank.easy;

import java.util.Arrays;
import java.util.List;

public class PositiveMinus {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(-4, 3, -9, 0, 4, 1);
        plusMinus(integers);
    }
    public static void plusMinus(List<Integer> list) {
        // Write your code here

        //
        double positiveCounter=0;
        double negativeCounter=0;
        double zeorCounter=0;
        int length= list.size();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)<0)
                negativeCounter++;
            else if(list.get(i)>0)
                positiveCounter++;
            else
            zeorCounter++;
        }
        System.out.println(positiveCounter/length);
        System.out.println(negativeCounter/length);
        System.out.println(zeorCounter/length);
    }
}
