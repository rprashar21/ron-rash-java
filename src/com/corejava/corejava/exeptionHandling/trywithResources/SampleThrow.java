package com.corejava.corejava.exeptionHandling.trywithResources;

public class SampleThrow {

    public static void main(String[] args) {

      // here we are properly handing the excepton
       validateVotingRights();

      // here we are terminating the program
      validate();
    }

    private static void validate() {
        int age =17;

        if(age<18)
        {
            // program will terminate becoz there is no catch block to catch this exception
            throw  new UserDefinedException("You cannot vote if your age is not above 18 ");
        }
    }

    private static void validateVotingRights() {

        try{
            int age =17;

            if(age<18)
            {
                // this is an unchecked exception
                // while it is not required to handle unchecked exceptions explicitly, it is still a good to handle these exceptions
                throw new UserDefinedException("You cannot vote if your age is not above 18 ");
            }
        }
        catch (UserDefinedException e)
        {
            System.out.println("hello");
            System.out.println(e);
        }
        catch (RuntimeException e)
        {
            System.out.println(e);
        }
        finally {
            System.out.println("please come back when your age is above 18");
        }
    }
}


