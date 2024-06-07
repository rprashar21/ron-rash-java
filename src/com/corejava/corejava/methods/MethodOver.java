package com.corejava.corejava.methods;

public class MethodOver {

    /*
    *    overloading                                             overrriding
    *   same name/diff parameters                             same name same parameters same return types different inherted classes
    *   may have diffe return type/access  modifiers          ovveride the behaviour of a parent class method
    *   or throw diff exceptions                                 should not throw new or broader exceptions
    *   resuabilty
    *
    *
    *
    *
    * */






    // class has multiple methods with same name // like a constructor
    // same name with different type of parameters or diff no of params
    // nothing with return type

    // By changing number of arguments and By changing the data type

    // You can have any number of main methods in a class by method overloading.
    // But JVM calls main() method which receives string array as arguments only.

    // it is compile time polymorphism
    public static  int cal(int param1){
        return 0;
    }
    // this is valid as parmTER IS DIFF
    public static  int cal(Integer param1){
        return 0;
    }

    // will throw an error
//    public static  float cal(int param1){
//        return 0;
//    }

    public void m1(int x){

    }

    public void m1(Integer x){

    }
}
