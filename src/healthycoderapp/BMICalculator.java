package healthycoderapp;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BMICalculator {

    private static final double BMI_THRESHOLD = 20.0;


    public boolean isDietNeeded(double weight, double height) {
        if (height == 0.0)
            throw new ArithmeticException(" hieght cannot be 0");
        double bmi = weight / (height * height);
        if (bmi < BMI_THRESHOLD)
            return false;
        return true;
    }

    public static Coder findCoderWithWorstBMI(List<Coder> coders) {
        return coders.stream().
                sorted(Comparator.comparing(BMICalculator::calculateBMI))
                .reduce((first, second) -> second).orElse(null);
    }

    public Coder findCodersWithWorstBmi(List<Coder> coders) {

        if (coders.isEmpty())
            return null;

      return coders.stream()
                .sorted(Comparator.comparing(BMICalculator::calculateBMI).
                        reversed()).collect(Collectors.toList()).get(0);


    }

    public static double[] getBMIScores(List<Coder> coders) {
        double[] bmiScores = new double[coders.size()];
        for (int i = 0; i < bmiScores.length; i++) {
            bmiScores[i] = BMICalculator.calculateBMI(coders.get(i));
        }
        return bmiScores;
    }

    private static double calculateBMI(Coder coder) {
        double height = coder.getHeight();
        double weight = coder.getWeight();
        if (height == 0.0)
            throw new ArithmeticException();
        double bmi = weight / (height * height);
        double result = Math.round(bmi * 100) / 100.0;
        return result;
    }

}
