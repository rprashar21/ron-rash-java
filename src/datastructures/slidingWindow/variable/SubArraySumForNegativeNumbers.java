package datastructures.slidingWindow.variable;

import java.util.HashMap;

public class SubArraySumForNegativeNumbers {
    public static void main(String[] args) {

//        int[] a= {10,15,5,-25,6,10,5,5,20,-41};
//        int[] a1 = {1, 2, 3, 4}; //
        int[] a = {10, -4, -1,5, -5}; //
        int target = 5;
        // 15 -10 = 5 whihc is in position 3&4

        // y = y-k+k
      //  int[] a1= {-2,-3,4,-1,-2,1,5,-3};

      //  subArraySum(a,target);
        longestSubArrayOfSum(a,5);
      // subArraySumUsingFreqMap(a,target);
       // bruteForce(a,target);
        int maxLength = longestSubArraySun(a, 5);
        System.out.println("max length is "+maxLength);
    }

    private static void longestSubArrayOfSum(final int[] a, final int target) {

        int start =0;
        int end =-1; // when we dont get the value
        int i=0;
        int sum =0;
        int maxLength = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while(i<a.length)
        {
            sum+=a[i];

            if(sum-target==0)
            {
                end=i;
                maxLength= Math.max(maxLength,end-start+1);
                System.out.println(maxLength);
            }
            if(map.containsKey(sum-target))
            {
                start= map.get(sum-target)+1;
                end = i;
                maxLength= Math.max(maxLength,end-start+1);
                System.out.println(maxLength);
            }
            map.put(sum,i);
            i++;
        }
        System.out.println(maxLength);
    }

    private static int longestSubArraySun(final int[] a, final int target){
        int index=0;
        int start =0;
        int end=0;
        int maxLength=0;
        int sum=0;
        while(index<a.length)
        {
           sum =sum+a[index];

           if(sum==target)
           {
               end=index;
               maxLength=Math.max(maxLength,(end-start)+1);
               start=end+1;
               sum=0;

           }
       index++;
        }
        return maxLength;
    }
}
