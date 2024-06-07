package datastructures.leetcode;

import java.time.LocalDate;

public class FutureBirthdate {

    public static void main(String[] args) {
        String birthDate = "23-10";
        FutureBirthdate.run(birthDate);
    }

    public static String run(String birthday_date) {
        /*
         * Write your code below; return type and arguments should be according to the problem's requirements
         */
        // take in the  bday  . -->birthdate 23-10-- // String s ="23-10"
        // extract the days and month
        // take a futuredates variable
        // 23 + 365 --> convert and check for which date it is falling
        // check if is a friday ,Sat sun and add to the future_dates

        // Extract the date and the month
        int days = Integer.parseInt(birthday_date.substring(0, 2));
        int months = Integer.parseInt(birthday_date.substring(3, 5));
        StringBuilder futureDates = new StringBuilder();
        LocalDate newDate = LocalDate.of(2016, months, days); // 23-10-2016

        for (int i = 0; i <= 54; i++) {
            int year = newDate.getYear();
            isLeapYear(year);
            int value = newDate.getDayOfWeek().getValue();
            if (value == 5 || value == 6 || value == 7) {
                futureDates = futureDates.append(newDate);
            }
            newDate = newDate.plusMonths(12);
        }
        return futureDates.toString();
    }

    private static void isLeapYear(final int year) {
        if ((year % 4 == 0) && year % 100 != 0) {
            System.out.println(year + " is a leap year");
        }
    }
}
