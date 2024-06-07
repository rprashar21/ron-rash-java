package datastructures.leetcode;

public class Sample {

    static int findMaxSum(int[] array,int maxStep){
        int sum = Integer.MIN_VALUE;
        int stepCount=0;

        for(int i=0;i<array.length;i++)
        {

            // checking for negative values
            if(array[i]<0 && maxStep<1)
            {
                //check how much you skip
                // max step <array.length
                i++;

            }
            sum=sum+array[i];
            final int maxSum = Math.max(sum, array[0]);
        }
        return sum;
    }
    public static void main(String[] args) {

        int[] array ={10,2,-10,-5,20};
        int maxStep=2;
        Sample.findMaxSum(array,maxStep);
    }
}
