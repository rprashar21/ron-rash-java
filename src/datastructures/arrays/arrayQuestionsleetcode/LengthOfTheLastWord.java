package datastructures.arrays.arrayQuestionsleetcode;

public class LengthOfTheLastWord {



    public static void main(String[] args) {
        String s =" the sky is  ";

        lengthOftheLastWord(s);
        System.out.println(lengthOftheLastWord(s));
        System.out.println(lengthOftheLastWordUsingCharArray(s));
    }

    private static int lengthOftheLastWord(final String s) {
        System.out.println(s.trim());
       String [] array =s.split(" ");
        return array[array.length - 1].length();
    }

    private static int lengthOftheLastWordUsingCharArray(final String s) {
        System.out.println(s.trim());
        String [] array =s.split(" ");
        char[] chars = array[array.length - 1].toCharArray();

        return chars.length;
    }
}
