package datastructures.strings;

public class CheckString {
    public static void main(String[] args) {

        String name ="Notice of Financial Penalty - Parent/Guardian - Notice of Financial Penalty - Parent/Guardian to Pay";
       // Parent / Guardian - No Distribution - Notice and Acknowledgement of Bind Over
       // Parent / Guardian - No Distribution - Notice and Acknowledgement of Bind Over
      // Case File - No Distribution - Warrant of Arrest (Offence)
      // . Defendant Parent - Sexual Harm Prevention Order
        if(name.contains("Parent")){
            System.out.println("exist");
        }
        else {
            System.out.println("find another way");
        }
    }
}
