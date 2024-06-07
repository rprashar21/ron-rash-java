package com.corejava.corejava.oops.thisvsSuper;


public class SampleThis {

    // this keyword is used with constructors/setters and optionally with getters
    // this() is uses to call a constructor from A OVERLOADED constructors in the class and can only be used in constructors
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(20,30);

        System.out.println("value of x will be 10 always "+rectangle);

        Rectangle rectangle1 = new Rectangle(100,20,30);


    }
}
class Rectangle{
    private int x;
    private int y;
    private int z;


    public Rectangle(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Rectangle(final int y, final int z) {
        this(10,y,z); // calls the first constructor and will alwYS HAVE A DEF VALUE FOR X
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}