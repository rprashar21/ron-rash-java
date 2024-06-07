package datastructures.arrays.arrayQuestionsleetcode;

public class BuySellStocks {

    public static void main(String[] args) {
        // best time to sell stocks
        // sliding window problem
        // maximise profit

        // a-->{7,1,5,3,6,4}
        // you loop thru the array assuming you bougth on the firstday
//        int currentProfit =(a[j] - a[i]);
//        if ( currentProfit> maxProfit)
//            maxProfit = currentProfit;

        // keep checking if the secondelem is < less than previous day
        // if yes first = second
//        if (a[j] < a[i]) {
//            i=j;
//            j++;
//        }
        // firstelem = second and
        // secondelem move forward
        // maxPorfit =5

        int a[] = {7, 1,5, 3, 6, 4};
    //   int[] a= {7,8,5,3,6,4};
        // max profit is 5

       // buySellStocks(a);
        buySellStocksNew(a);
        System.out.println(buySellStocksNew(a));
        //System.out.println(buySellStocks(a));
    }

    private static int buySellStocksNew(final int[] a) {

        if(a.length==1)
            return 0;

        int i=0;
        int maxProfit = 0;
        for(int j=1;j<a.length;j++)
        {
            int currentProfit = a[j]-a[i];
            maxProfit = Math.max(maxProfit,currentProfit);

            if(a[j]<a[i])
            {
                i=j;
            }
        }
       return maxProfit>0?maxProfit:0;
    }


    private static int buySellStocks(final int[] a) {

        if(a.length==1)
            return 0;

        int i = 0, j = 1;
        int maxProfit = Integer.MIN_VALUE;
        while (j < a.length ) {
             int currentProfit =(a[j] - a[i]);
            if ( currentProfit> maxProfit)
                maxProfit = currentProfit;

            if (a[j] < a[i]) {
                i=j;
                j++;
            }
            else
            j++;
        }

        return maxProfit<0?0:maxProfit;
    }

    private void buySellStocksNormal(final int[] a){

        if(a.length==0)
        {
            System.out.println("");
        }


        int maxProfit = Integer.MIN_VALUE;
        int i =0;
        for(int j=1;j<a.length;j++)
        {

            int diff = a[j]-a[i];
            maxProfit=Math.max(diff,maxProfit);

            if(a[j]>a[i])
                i=j;
        }
        System.out.println(maxProfit);
    }
}
