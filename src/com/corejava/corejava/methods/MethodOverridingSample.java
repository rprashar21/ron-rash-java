package com.corejava.corejava.methods;

public class MethodOverridingSample {

    // subclass has the same methods of its parent class
    // same name and same parameters for overriding and must be is -a relationship

    // when we want a specific implementaton we use method overrriding

    // static methods can be overloaded but not overriden bcz static methods belong to the class and not instance o
    // It is because the static method is bound with class whereas instance method is bound with an object.
    // Static belongs to the class area, and an instance belongs to the heap area.

    // Method overriding is the example of run time polymorphism.
    public static void main(String[] args) {
        SBI sbi =new SBI();
        System.out.println("rate of interest for sbi is "+sbi.getRateOInterest(2));

        PNB pnb =new PNB();
        System.out.println("rate of interest for pnb is "+pnb.getRateOInterest(2));

        RBI rbi =new RBI();
        System.out.println("rate of interest for rbi is "+rbi.getRateOInterest(2));
    }
}

class RBI{

    public float getRateOInterest(int roi )
    {
        return roi * 12.43f *10;
    }
}

class SBI extends RBI{

    public float getRateOInterest(int roi )
    {
        return roi * 12.43f *4; // overriden method but diff or specific implementation
    }
}

class PNB extends RBI{

    public float getRateOInterest(int roi )
    {
        return roi * 12.43f * 20; // overriden method but diff or specific implementation
    }
}
