package datastructures.arrays.arraysHashing;

import java.util.Arrays;
import java.util.TreeSet;

public class LongestSequenceByTreeSet {

    public static void main(String[] args) {
        int[] a ={100,4,200,3,1,2};

        longestStreak(a);
    }

    public static int longestStreak(final int[] a){

        TreeSet<Integer> treeSet = new TreeSet<>();
        Arrays.stream(a).forEach(elem->{
            treeSet.add(elem);
        });
        int i=0;
        int finalLength=0;
        while(i<a.length)
        {
            int currentLength =1;
            if(i+1<a.length && a[i+1]-a[i]==1){
                currentLength++;
            }
           finalLength=Math.max(currentLength,finalLength);
            i++;
        }

        System.out.println(treeSet+" "+finalLength);
        return finalLength;
    }
}
