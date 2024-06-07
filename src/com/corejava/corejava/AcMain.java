package com.corejava.corejava;

public class AcMain {
    public static void main(String[] args) {
        Ac ac = new Ac();
        System.out.println(ac.hello());
    }
}
class Ac{

    int hello()
    {
        try{
            return 2;
        }finally {
            System.out.println("hello");
        }
    }
}