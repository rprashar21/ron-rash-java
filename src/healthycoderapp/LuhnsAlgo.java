package healthycoderapp;


public class LuhnsAlgo {

    static boolean isValidCreditCard(String card){


        if(null == card || card.isEmpty() || card.length()!=16)
            throw new RuntimeException("please enter a proper value ");

        final char[] chars = card.toCharArray();
        int sum=0;
        boolean isSecond = false;
        for(int i=chars.length-1;i>=0;i--)
        {
            char ch =card.charAt(i);
            if( !Character.isDigit(ch))
                throw new RuntimeException("card has invalid characters");

            int d = card.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            sum += d / 10;
            sum += d % 10;

            isSecond = !isSecond;
        }
        return sum%10==0;

    }
}
