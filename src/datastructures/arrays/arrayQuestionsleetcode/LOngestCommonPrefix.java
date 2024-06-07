package datastructures.arrays.arrayQuestionsleetcode;

public class LOngestCommonPrefix {
//    If there is no common prefix, return an empty string "".
//
//
//
//    Example 1:
//
//    Input: strs = ["flower","flow","flight"]
//    Output: "fl"
//    Example 2:
//
//    Input: strs = ["dog","racecar","car"]
//    Output: ""
//    Explanation: There is no common prefix among the input strings.
//}

    public static void main(String[] args) {
      //  String[] array = {"dog","racecar","car"};
        String[] array = {"cab","cac","cab"};

        System.out.println(ActualSolution(array));
    }

    private static String findLongestCommonPrefix(final String[] array) {
        StringBuilder sb = new StringBuilder("");
        int length = array[0].length();
        int j;
        for (int i = 0; i < length; i++) {
            j = i+1;
            while (j < array.length && array[0].charAt(i) == array[j].charAt(i)) {
                j++;
                if (j == array.length) {
                    sb.append(array[i].charAt(i));
                }
            }
        }
        System.out.println(sb);
        return sb.toString().length()>0?sb.toString():"";
    }
    private static String ActualSolution(final String[] array) {
        if (array == null || array.length == 0) return "";

        for (int i = 0; i < array[0].length(); i++)
        {
            char c = array[0].charAt(i);

            for (int j = 1; j < array.length; j++)
            {
                if (i == array[j].length() || array[j].charAt(i) != c) {
                    // Return the substring from 0 to i because i is the length of the common prefix
                    return array[0].substring(0, i);
                }
            }
        }

        return array[0];
    }
}
