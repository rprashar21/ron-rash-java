package collections.exampless;

import java.util.Arrays;

public class MainApp {

    public static void main(String[] args) {

        int[] array = {1,2,3,4,5,6,};

        for(int k= array.length-1;k>-1;k--)
        {

        }

        char[] str = {'h','e','l','l','o'};
        SampleProgram sampleProgram1 = new SampleProgram();
        SampleProgram sampleProgram2 = new SampleProgram();
        SampleProgram sampleProgram3 = new SampleProgram();
        sampleProgram3.name="sonu";
        System.out.println(sampleProgram3.name);
        System.out.println(sampleProgram2.name);

        sampleProgram3.reverseString(str);
        System.out.println(SampleProgram.title="Prashar");

        int[] reverseArray = sampleProgram3.reverseArray(array);
        sampleProgram3.reverseString(str);
        System.out.println(Arrays.toString(reverseArray));



    }
}
