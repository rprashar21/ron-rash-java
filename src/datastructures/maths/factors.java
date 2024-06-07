package datastructures.maths;

public class factors {
    public static void main(String[] args) {

        factor(14);
    }

    private static void factor(final int n) {
        for(int i =1;i<n;i++)
        {
            if(n%i==0)
                System.out.println("factor of "+n+" is "+i);
        }
    }
}
