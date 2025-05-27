package datastructures.slidingWindow.variable;

public class LongestPalindromeSubstring {

    //Input: s = "babad" Output: "bab"

    //Input: s = "cbbd"
    //Output: "bb"

    public static void main(String[] args) {
        longestPalindrome("abba");
    }

    public static  String longestPalindrome(String s) {

        // lets start from the middle and then go in both direction and see where we end up
        if(s == null || s.length() == 0) return "";

        int start = 0, end = 0;

        for(int i = 0; i < s.length(); i++) {

        }

        return null;
    }
}
