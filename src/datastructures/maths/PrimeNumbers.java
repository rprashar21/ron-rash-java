package datastructures.maths;

public class PrimeNumbers {
    // numbers which are divisbile by only itslef and one

    public static void main(String[] args) {

        // 12 - factors of 12 1*12, 2*6 , 3*4 , 4*3, 12*1
        // unique factores 1,2,3,4
        // the prime number should be divided before its square root ,if it is not divisbble it will not be found

        findPrime(12);
        findPrime(13);
        findPrime(17);
        findPrime(49);
    }

    private static void findPrime(final int number) {

        // here we are not checkking number divisble by 1 and itself ,, becoz every number is div by 1 nad itsdelf
        int count =0;
        for(int i=2;i*i<=number;i++)
        {
            if(number%i==0)
            {
                count++;
                break;
            }
        }
        if(count ==0)
        {
            System.out.println(number+" is prime");
        }
        else
        {
            System.out.println(number +" is not prime");
        }
    }
}
