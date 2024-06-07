package com.corejava.corejava.datatype;

public class TrickyProblems {
    //https://medium.com/thefreshwrites/java-tricky-interview-questions-and-answers-3453b8d5fdc0
    public static void main(String[] str){
        Integer number_1 = 50;
        Integer number_2 = 50;
        Integer number_3 = 150;
        Integer number_4 = 150;

        if(number_1 == number_2)
            System.out.println("Number 1 and Number 2 are Equal");
        else
            System.out.println("Number 1 and Number 2 are notEqual");

        if(number_3 == number_4)
            System.out.println("Number 3 and Number 4 are Equal");
        else
            System.out.println("Number 3 and Number 4 are not Equal");

        System.out.println('j' + 'a' + 'v' + 'a'); // this will return the ascii values 106 +97 +118+97 = 418

      // Number 1 and Number 2 are Equal
        //Number 3 and Number 4 are not Equal
    }
//
//    We used to believe that when two object references were compared with “==”, the result was always “false.”
//    However, in this case, integer caching alters the outcome.
//    The integer class has a caching range of -128 to 127. When a number falls within this range and autoboxing is utilized,
//    it allocates the same reference. As a result, for the number 50, both number 1 and number 2 will have the same reference,
//    but for the value 150 (not in the range of -128 to 127), number 3 and number 4 will have distinct references.
}
