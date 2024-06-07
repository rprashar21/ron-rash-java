package datastructures.hackerrank.easy;

import java.util.Arrays;
import java.util.List;

public class CountSorting {

    public static void main(String[] args) {

       countingSort( Arrays.asList(1,1,3,2,1));
    }
    public static List<Integer> countingSort(List<Integer> list) {
        // Write your code here

        // kinda cylcic sort
        // create a frequency array
        Integer[] freq = new Integer[100];

        //filling the frequency array with  0 since the above one is an object and will have null values

        for(int i=0;i<100;i++)
        {
            freq[i]=0;
        }

        // loop thru the list and fill the postion of each element with their count
        for(int i=0;i<list.size();i++)
        {
            freq[list.get(i)]++;
        }

        // since return is alist type convert the freq array to list
        return Arrays.asList(freq);
    }

}

