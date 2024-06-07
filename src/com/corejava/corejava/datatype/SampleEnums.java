package com.corejava.corejava.datatype;

enum DayOfTheWeek{
    SUNDAY,MONDAY,TUESDAY;

        // enums are special type of class which contains an array of constants
    // enums are constant also instance of the class and we can add fields methods and constructors to the enum
}

enum Toppings{

    TOMATO,
    CHEDDAR,
    CHEESE,
    GARLIC;

    public double getPrice(){
        if(this==CHEESE)
            return 1.509;
        return 10.0;
    }
}
public class SampleEnums {


    public static void main(String[] args) {
        DayOfTheWeek monday = DayOfTheWeek.MONDAY;
        System.out.println(monday);

        System.out.println(monday.name() + " "+monday.ordinal());
        System.out.println("let the game begin---------");
          for(DayOfTheWeek day : DayOfTheWeek.values())
          {
              dayOfThWeek(day);
          }

          for(Toppings toppings :Toppings.values())
          {
              System.out.println(toppings.name()+" "+toppings.getPrice());
          }

    }


    public static void dayOfThWeek(DayOfTheWeek day)
    {

        switch (day){
            case MONDAY:
                System.out.println("Boring is "+day);
                break;
            case SUNDAY:
                System.out.println("Funday is "+day);
                break;
            default:
                System.out.println("Wait for weeked ");
                break;
        }
    }
}

