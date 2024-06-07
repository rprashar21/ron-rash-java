package com.corejava.corejava.comparableandcomparator.assignmet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Review {
    private double rating;
    private String comment;

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Review(final double rating, final String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public static void main(String[] args) {
        Review review1 = new Review(4.2,"Good content");
        Review review2 = new Review(4.9,"Awesome content");
        Review review3 = new Review(3.1,"Average content");
        Review review4 = new Review(2.9,"Bad content");

        List<Review> reviewList = Arrays.asList(review1, review2, review3, review4);

        // sorting the list based on rating

       // Collections.sort(reviewList,new RatingComparator());
        reviewList.sort(Comparator.comparingDouble(Review::getRating));
        System.out.println(reviewList);

    }

}
class RatingComparator implements Comparator<Review>
{

    @Override
    public int compare(final Review review1, final Review review2) {
        return Double.compare(review2.getRating(),review1.getRating());
    }
}
