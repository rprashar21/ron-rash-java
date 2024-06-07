package com.corejava.corejava;

public class Overloading {


    void accept(Integer a)
    {
        System.out.println("inside a");
    }
    void accept(String  s)
    {
        System.out.println("inside s");
    }

    public static void main(String[] args) {
        Overloading obj = new Overloading();
      //  obj.accept(null); -- this will lead to compiler error
    }
}
