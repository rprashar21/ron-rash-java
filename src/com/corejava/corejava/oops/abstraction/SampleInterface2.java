package com.corejava.corejava.oops.abstraction;

public class SampleInterface2 {

    // inetrface can have default methods ,, but when 2 interfaces have same method how can we resolve that

    public static void main(String[] args) {
        Sample2 sample2 = new Sample2();
        sample2.m1();
    }

}

interface Left{

    default void m1(){
        System.out.println("def method of letf interface");
    }
}
interface Right{

    default void m1(){
        System.out.println("def method of right interface");
    }
}

class Sample2 implements Left,Right{

    // this will throw error bcz both interfaces have the same method and jvm is confused
    // how to fix provide on implementation
    @Override
    public void m1() {

        //own implementation
        System.out.println("overriden implemnation ");
      //  Left.super.m1(); -- calling left intercae  default method InterfaceName.super.methodName
      Right.super.m1();
    }



}