package datastructures.arrays.arrayQuestionsleetcode;

public class HouseRobber {

    public static void main(String[] args) {
        int[] a = {2,7,9,3,1};

       // int[] a = {2,1,1,2};
        int robbery = rob(a);
        System.out.println("roberry worth is "+rob(a));

    }

    public static int rob(int[] nums) {

        int evenSum=0;
        int oddSum=0;

        for(int i=0;i< nums.length;i++)
        {
            if(i%2==0)
             evenSum+=nums[i];
            else
             oddSum+=nums[i];
        }

        return evenSum>oddSum?evenSum:oddSum;
    }
}
