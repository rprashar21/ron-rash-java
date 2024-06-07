package datastructures.arrays.leetcodeArrays;

public class BuyAndSellStocks2 {
    public static void main(String[] args) {

//        On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
//        However, you can buy it then immediately sell it on the same day.


        int a[] = {7, 1, 5, 3, 6, 4};

        System.out.println(        buyAndSellStokcs(a));


    }

    private static int buyAndSellStokcs(final int[] a) {

        int i = 0;
        int j =1;
        int m =1;
        int k=0;

        int maxProfit =0;
        int profit =0;

        while(j<a.length)
        {
            maxProfit = Math.max(a[j]-a[i],maxProfit);
            if(a[j]<a[i])
                i=j;

            j++;
        }
        System.out.println(maxProfit);

        while(m<a.length)
        {
         if(a[m]<a[k])
         {
            k++;
            m++;
             continue;
         }
         profit= profit+a[m]-a[k];
         k++;
         m++;
        }
        System.out.println(profit);

        return Math.max(profit,maxProfit);
    }
}
