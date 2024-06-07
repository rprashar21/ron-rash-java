package datastructures.recursion.questions;

public class RecursionCountNoOfZeros {
    public static void main(String[] args) {

        // normal without recursion
        int n = 03; // 3
        int count =0;
        while(n!=0) // think about the base condition
        {
            int mod = n%10;
            if(mod == 0)
                count++;
            n=n/10;
        }
        System.out.println(count);

       int number = 500009;

       int counter = 0;

        System.out.println(countZeros(number,counter));

    }

    private static int countZeros(int number,  int counter) {

        if(number<10 || number ==0)
            return counter++;
        else
        {
            if(number%10==number)
                return counter;

            int mod = number%10;
            if(mod==0)
                counter++;
            return countZeros(number/10,counter);
        }

    }

}
