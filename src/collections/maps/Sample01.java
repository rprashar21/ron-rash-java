package collections.maps;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corejava.corejava.equalsandhascodes.Student;

public class Sample01 {

    public static void main(String[] args) {


        // suppose i have a deptid --> mapped to a list of students and i want to check if a aprticalr department has an studenst or not

        List<String> deptList = Arrays.asList("maths","physics","sports ");

        List<String> stdu1 = Arrays.asList("ron","don");
        List<String> stdu2 = Arrays.asList("jan","ban");
        List<String> stdu3 = Arrays.asList("dan","canban");

        Map<String, List<String>> studentMap = new HashMap<>();

        for (String dept : deptList) {

            // we will come back to this one min
        }
        System.out.println(studentMap);
    }



}
