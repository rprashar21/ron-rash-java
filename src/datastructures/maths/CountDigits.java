package datastructures.maths;

public class CountDigits {
    public static void main(String[] args) {

        int n =7534;
        int counter=0;
        while(n>0) //   n!=0
        {
            System.out.println(n%10); // mod will
           n= n/10;

           counter++;
        }
        System.out.println(counter);
        int number = 7534; // print 7,5,5,4
        // number 7534 / 1000 --- 534 eleminates 7 number is 534
        // number / 10 ------ give last number 4 number is 753


        while(number!=0)
        {
            int divisor = (int)Math.pow(10,counter-1);
            int quotient =number/divisor ;// 7543/1000 ,, 534/100  // first it gives 7 --- 534
            System.out.print(quotient+" , ");

          number=number%divisor;
            counter--;
        }

    }


}
