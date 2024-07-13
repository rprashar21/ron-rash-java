import java.util.ArrayList;
import java.util.List;

public class TradingBot {

    static boolean isNewBar = false;
    //previous candle
    static int prevBars = 0;
    static double swing_H = -1.0, swing_L = -1.0;
    static final int LENGTH = 5;
    static final int LIMIT = 5;

    static List<Candle> candles = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize with some example data
        initializeCandles();

        // Main loop to simulate OnTick event
        for (int i = 0; i < candles.size(); i++) {
            onTick();
        }
    }

    public static void initializeCandles() {
        // Add some example candle data
        //
        candles.add(new Candle(1.1, 1.0, 1.05, 1.02, 1000));
        candles.add(new Candle(1.2, 1.05, 1.1, 1.06, 1001));

        candles.add(new Candle(1.4, 1.0, 1.05, 1.02, 1000));
        candles.add(new Candle(1.5, 1.05, 1.1, 1.06, 1001));

        // Add more candles as needed
    }

    public static void onTick() {
        int currBars = candles.size(); // 2
        if (prevBars == currBars) {
            isNewBar = false;
        } else if (prevBars != currBars) {
            isNewBar = true;
            prevBars = currBars;
        }

        int currBarIndex = currBars - 1; // Current bar index is the last one

        if (isNewBar) {
            boolean isSwingHigh = true, isSwingLow = true;

            for (int j = 1; j <= LENGTH; j++) {
                int rightIndex = currBarIndex - j;
                int leftIndex = currBarIndex + j;

                if (rightIndex < 0 || leftIndex >= candles.size()) break;

                if (candles.get(currBarIndex).high <= candles.get(rightIndex).high
                        || candles.get(currBarIndex).high < candles.get(leftIndex).high) {
                    isSwingHigh = false;
                }
                if (candles.get(currBarIndex).low >= candles.get(rightIndex).low
                        || candles.get(currBarIndex).low > candles.get(leftIndex).low) {
                    isSwingLow = false;
                }
            }

            if (isSwingHigh) {
                swing_H = candles.get(currBarIndex).high;
                System.out.println("UP @ BAR INDEX " + currBarIndex + " of High: " + candles.get(currBarIndex).high);
                drawSwingPoint("SwingHigh", candles.get(currBarIndex).time, candles.get(currBarIndex).high, 77, "Blue", -1);
            }
            if (isSwingLow) {
                swing_L = candles.get(currBarIndex).low;
                System.out.println("DOWN @ BAR INDEX " + currBarIndex + " of Low: " + candles.get(currBarIndex).low);
                drawSwingPoint("SwingLow", candles.get(currBarIndex).time, candles.get(currBarIndex).low, 77, "Red", 1);
            }
        }

        double ask = getAsk();
        double bid = getBid();

        if (swing_H > 0 && bid > swing_H && getClose(1) > swing_H) {
            System.out.println("BREAK UP NOW");
            swing_H = -1.0;
            // Open Buy
            System.out.println("Executing Buy Order");
        } else if (swing_L > 0 && ask < swing_L && getClose(1) < swing_L) {
            System.out.println("BREAK DOWN NOW");
            swing_L = -1.0;
            // Open Sell
            System.out.println("Executing Sell Order");
        }
    }

    static double getHigh(int index) {
        return candles.get(candles.size() - 1 - index).high;
    }

    static double getLow(int index) {
        return candles.get(candles.size() - 1 - index).low;
    }

    static double getClose(int index) {
        return candles.get(candles.size() - 1 - index).close;
    }

    static long getTime(int index) {
        return candles.get(candles.size() - 1 - index).time;
    }

    static double getAsk() {
        // Simulate fetching the ask price
        return 1.1;
    }

    static double getBid() {
        // Simulate fetching the bid price
        return 1.0;
    }

    static void drawSwingPoint(String objName, long time, double price, int arrCode, String color, int direction) {
        // Simulate drawing on the chart
        System.out.println("Drawing Swing Point: " + objName + " at time: " + time + " with price: " + price);
    }

    static void drawBreakLevel(String objName, long time1, double price1, long time2, double price2, String color, int direction) {
        // Simulate drawing on the chart
        System.out.println("Drawing Break Level: " + objName + " from time: " + time1 + " to time: " + time2 + " with price: " + price1 + " to " + price2);
    }

    static class Candle {
        double high;
        double low;
        double open;
        double close;
        long time;

        Candle(double high, double low, double open, double close, long time) {
            this.high = high;
            this.low = low;
            this.open = open;
            this.close = close;
            this.time = time;
        }
    }
}
