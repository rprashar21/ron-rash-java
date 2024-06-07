package datastructures.leetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RockPaperScissor {
    public static void main(String[] args) throws IOException {
        List<Long> list = new ArrayList<Long>();
        File file = new File("/Users/rohitprashar/Downloads/react-aws/Input01.txt");
        BufferedReader reader = null;
        // A, X -> Rock = 1 point
        // B, Y -> Paper = 2 points
        // C, Z -> Scissor = 3 points

        // A ,Y --> 1,2 -->

        //Win = 6 points
        //Draw = 3 points
        //Loose = 1 point

//        char[] arr = new char[2];
        long totalPointsPart1 = 0;
        long totalPointsPart2 = 0;
        int win = 6;
        int draw = 3;
        int loss = 0;

        Map<String, Integer> map = new HashMap<>();
        map.put("X", 1);
        map.put("Y", 2);
        map.put("Z", 3);


        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                String[] arr = text.split(" ");

                // Part 1


                // Part 2
                //Loose
                if(arr[1].equals("X")) {
                    if(arr[0].equals("A")) {
                        totalPointsPart2 += map.get("Z");
                    } else if(arr[0].equals("B")) {
                        totalPointsPart2 += map.get("X");
                    } else if(arr[0].equals("C")) {
                        totalPointsPart2 += map.get("Y");
                    }

                    totalPointsPart2 += loss;
                }
                // Draw
                else if(arr[1].equals("Y")) {
                    if(arr[0].equals("A")) {
                        totalPointsPart2 += map.get("X");
                    } else if(arr[0].equals("B")) {
                        totalPointsPart2 += map.get("Y");
                    } else if(arr[0].equals("C")) {
                        totalPointsPart2 += map.get("Z");
                    }

                    totalPointsPart2 += draw;
                }
                // Win
                else if(arr[1].equals("Z")) {
                    if(arr[0].equals("A")) {
                        totalPointsPart2 += map.get("Y");
                    } else if(arr[0].equals("B")) {
                        totalPointsPart2 += map.get("Z");
                    } else if(arr[0].equals("C")) {
                        totalPointsPart2 += map.get("X");
                    }
                    totalPointsPart2 += win;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        System.out.println("totalPointsPart1: " + totalPointsPart1);
        System.out.println("totalPointsPart2: " + totalPointsPart2);
    }
}
