package datastructures.binary.Questions;

import java.util.Arrays;

public class KokoEatingBananas2 {

    public static void main(String[] args) {

        int[] piles ={30,11,23,4,20};

        int minEatingSpeed = minEatingSpeed(piles, 5);

    }

    public static int minEatingSpeed(int[] piles, int H) {
        int left = 1; // Minimum eating speed
        int right = Arrays.stream(piles).max().getAsInt(); // Maximum eating speed

        while (left < right) {
            int mid = left + (right - left) / 2;
            int hours = calculateHours(piles, mid);

            if (hours > H) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
        return left;
    }

    private static int calculateHours(int[] piles, int speed) {
        int hours = 0; //30,11,23,4,20
        for (int pile : piles) {
            hours =hours+ (pile + speed - 1) / speed;
        }
        return hours;
    }

}
