package datastructures.leetcode.sortingQuestions;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
public class FlippingMatrix {


        public static void main(String[] args) {
            // define the original list
            List<List<Integer>> list = new ArrayList<>();
            list.add(Arrays.asList(1, 2, 3));
            list.add(Arrays.asList(4, 5, 6));
            list.add(Arrays.asList(7, 8, 9));

            // convert the list to a 2D array
            int rows = list.size();
            int cols = list.get(0).size();
            int[][] matrix = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = list.get(i).get(j);
                }
            }

            // get the upper quadrant with maximal value after flipping the matrix
            int[][] newMatrix = new int[cols][rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    newMatrix[j][i] = matrix[i][j];
                }
            }
            int maxSum = 0;
            int[][] upperQuadrant = new int[cols/2][rows/2];
            for (int i = 0; i < cols/2; i++) {
                for (int j = 0; j < rows/2; j++) {
                    int sum = newMatrix[i][j] + newMatrix[i][rows-1-j] + newMatrix[cols-1-i][j] + newMatrix[cols-1-i][rows-1-j];
                    if (sum > maxSum) {
                        maxSum = sum;
                        upperQuadrant[0][0] = newMatrix[i][j];
                        upperQuadrant[0][1] = newMatrix[i][rows-1-j];
                        upperQuadrant[1][0] = newMatrix[cols-1-i][j];
                        upperQuadrant[1][1] = newMatrix[cols-1-i][rows-1-j];
                    }
                }
            }

            // print the upper quadrant with maximal value
            for (int i = 0; i < upperQuadrant.length; i++) {
                for (int j = 0; j < upperQuadrant[0].length; j++) {
                    System.out.print(upperQuadrant[i][j] + " ");
                }
                System.out.println();
            }
        }
    }


