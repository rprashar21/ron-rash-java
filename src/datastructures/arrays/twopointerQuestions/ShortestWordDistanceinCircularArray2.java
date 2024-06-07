package datastructures.arrays.twopointerQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestWordDistanceinCircularArray2 {

    public static void main(String[] args) {
                                             //  0   1     2    3   4    5     6
        List<String> stringList = Arrays.asList("a0","a1","a6","a3","a4","a5","a6");
        String start ="a5";
        String target = "a6";

        // from startIndex find min distance for target
        // find tthe statIndex and target index
        int startIndex=-1;
        int targetIndex = -1;
        int min=Integer.MIN_VALUE;
        for(int i=0;i< stringList.size();i++)
        {
            if(stringList.get(i).equals(start))
                startIndex=i;
            if(stringList.get(i).equals(target))
                targetIndex=i;

            if(targetIndex!=-1 && startIndex!=-1)
            {
                int diff = Math.abs(targetIndex-startIndex);
                 min = Math.min(diff,Math.abs(stringList.size()- diff));
            }
        }
        System.out.println(min);
    }


}
