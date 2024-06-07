package com.corejava.corejava.inheritance.challengeOne;

public class HourlyEmployee extends Employee{

    // new field hourlpayrate
    // name,birthdate comes from worker class
    // hriedate employee
    // hourpayrate is bcz he will get paid on his hours worked

    private double hourlyPayRate;

    public HourlyEmployee(final String name, final String birthdate, final String hireDate, final double hourlyPayRate) {
        super(name, birthdate, hireDate);
        this.hourlyPayRate = hourlyPayRate;
    }


    //

    @Override
    public double collectPay() {
     //   return super.collectPay(); want my own implemenation
        return 8*this.hourlyPayRate;
    }
}
