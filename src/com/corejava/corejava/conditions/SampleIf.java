package com.corejava.corejava.conditions;


public class SampleIf {

    public static void main(String[] args) {




      // condition(90);
        condition(2);
      //  condition(70);
    }

    private static void condition(final int marks) {
        if(marks>90)
        {
            System.out.println("excellent");
        }
        else if(marks > 70)
        {
            System.out.println("very good ");
        }
        else if(marks> 75 )
        {
            System.out.println("good");
        }
        else if(marks> 60 && marks < 70 )
        {
            System.out.println("average");
        }
        else
        {
            System.out.println("bad");
        }
    }
}
