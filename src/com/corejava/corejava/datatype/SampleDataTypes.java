package com.corejava.corejava.datatype;

public class SampleDataTypes {
    public static void main(String[] args) {
        Float f1 = new Float("3.0");
        int x = f1.intValue();
        byte b = f1.byteValue   ();
        double d = f1.doubleValue();
        System.out.println(x+b+d);
        int i =010;
        int j=07;
        System.out.println(i);
    }
}
interface BaseI{
    void method();
}
class BasceC{
    public void method(){
        System.out.println("inside");
    }
}
class ImplC extends  BasceC implements BaseI{

}