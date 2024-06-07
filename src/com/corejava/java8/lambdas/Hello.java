package com.corejava.java8.lambdas;

import java.time.LocalDate;
import java.util.Objects;

public class Hello {
    public static void main(String[] args) {
      Hello hello = new Hello();
        ReadyCase readyCase = new ReadyCase("rohit");
        LocalDate postDate = LocalDate.of(2024,05,01);
        String expiryDateForCase = hello.getExpiryDateForCase1(null,postDate);
        System.out.println("date "+expiryDateForCase);
    }

    private String getExpiryDateForCase(final ReadyCase readyCase, final LocalDate postingDate) {
        if (Objects.isNull(readyCase)) {
            LocalDate currentDate = LocalDate.now();
            LocalDate futureExpiryDate = postingDate.plusDays(28);

            if (currentDate.isBefore(postingDate.plusDays(28))){
                return String.valueOf(futureExpiryDate);
            }
        }
        return "";
    }


    private String getExpiryDateForCase1(final ReadyCase readyCase, final LocalDate postingDate) {
        // Check if the case is not ready (i.e., readyCase is null)
        if (Objects.isNull(readyCase)) {
            LocalDate currentDate = LocalDate.now();
            LocalDate futureExpiryDate = postingDate.plusDays(28);  // Calculate the expiry date once

            // Check if the current date is before the future expiry date
            if (currentDate.isBefore(futureExpiryDate)) {
                // Return the future expiry date in ISO-8601 format (e.g., 2023-05-14)
                return futureExpiryDate.toString();
            } else {
                // If the current date is on or after the expiry date, indicate that the expiry date has passed
                return "Expiry date has passed (" + futureExpiryDate.toString() + ")";
            }
        }
        // Provide a default message or a more specific status if the case is ready or no expiry date is needed
        return "No expiry date needed or case is already ready.";
    }

}

class ReadyCase{
private String name;

    public ReadyCase(final String name) {
        this.name = name;
    }
}




