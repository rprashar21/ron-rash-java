package datastructures.arrays.twopointerQuestions;

public class Palindrome {

    public static void main(String[] args) {
        String s ="0P";
        // remove all special character s
        String newString="";
        for(int i=0;i<s.length();i++)
        {
            if(Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i)))
            {
                newString=newString.concat(String.valueOf(s.charAt(i)).toLowerCase());
            }
        }
        System.out.println(newString);
      char[] chars = newString.toCharArray();
        // reverse a string
        int start=0;
        int end = chars.length-1;
        while(start<=end)
        {
            char temp = chars[end];
            chars[end]=chars[start];
            chars[start]=temp;
            start++;
            end--;

        }
        String reverse = new String(chars);
        System.out.println(reverse);
        if(reverse.equals(newString))
            System.out.println("equal");
        else
            System.out.println("not equal");

    }
}
