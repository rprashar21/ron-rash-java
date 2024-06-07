package datastructures.greedy;

public class MaxProductArray {
    public static void main(String[] args) {

//        int[] array = {2,3,-2,4};
//        int[] array = {2,3,-2,-5,6,-1,4};
        int[] array = {2,3,0,-5,6,-1,4};
        // here we will have to check for 3 conditions
        //1, for zero values
        // 2. is for -ve number of -ve
        // 3. positive number of -ve becoz - *- = +
        maxProductArray(array);
    }

    private static void maxProductArray(final int[] array) {
        /*
        * left prefix and right prefix  and we will keep calultaing values from left side and the right side
        *
        * */
        int leftProd =1;
        int rightProd =1;
        int j = array.length-1;
        int maxProd = Integer.MIN_VALUE;
        for(int i=0;i< array.length;i++)
        {
            leftProd = leftProd*array[i];

            if(leftProd==0)
                leftProd=1;

            rightProd = rightProd*array[j];
            if(rightProd==0)
                rightProd=1;

            maxProd = Math.max(leftProd,rightProd);
            j--;
        }
        System.out.println("maximum Product in subrray is "+maxProd);
    }

}
