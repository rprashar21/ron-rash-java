package datastructures.hackerrank.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparseArrays {
    /*
     * Complete the 'matchingStrings' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY strings
     *  2. STRING_ARRAY queries
     */
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("ab", "ab", "abc");
        List<String> queryList = Arrays.asList("ab", "abc", "bc");

        // we need to fidn the count of querylist in string list [2,1,0] -- ab and abc is twice in striglist bc is 0 times hence [2,1,0]
        System.out.println(matchingStrings(stringList,queryList));

        optimizedWay(stringList,queryList);
    }

    private static void optimizedWay(final List<String> stringList, final List<String> queryList) {

        for (String s : queryList)
        {
            stringList.contains(s);

        }
    }

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        // Write your code here
        // create a hashmap
        Map<String,Integer> map = new HashMap<>();

        //
        List<Integer> list = new ArrayList<>();
        for(String s: strings)
        {
            map.put(s,map.getOrDefault(s, 0)+1);
        }
        for(String s :queries)
        {
            if(map.containsKey(s))
            {
                list.add(map.get(s));
            }
            else{
                list.add(0);
            }
        }
        return list;
    }

}
