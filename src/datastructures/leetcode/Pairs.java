package datastructures.leetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pairs {

    public static void main(String[] args) throws FileNotFoundException {
//        2-4,6-8
//        2-3,4-5
//        5-7,7-9
//        2-8,3-7
//        6-6,4-6
//        2-6,4-8



        try(BufferedReader reader = new BufferedReader(new FileReader("/Users/rohitprashar/Downloads/javares/src/main/resources/input01.txt"));) {
            String text;
            int count =0;
            while ((text = reader.readLine()) != null) {

                String[] arr = text.split(",");


                String[] firstPair = arr[0].split("-");
                String[] secondPair = arr[1].split("-");

                Integer a = Integer.parseInt(firstPair[0]);
                int b = Integer.parseInt(firstPair[1]);
                int c = Integer.parseInt(secondPair[0]);
                int d = Integer.parseInt(secondPair[1]);

                if(a >= c && a <=d  && b>=c && b <=d)
                    count++;
                if(c >= a && c <=b  && d>=a && d <=b)
                    count++;



            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }

