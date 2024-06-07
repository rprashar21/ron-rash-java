package com.corejava.corejava.inheritance.challengeOne;

import java.time.LocalDate;

public class Worker {
    private String name;
    private String birthdate;
    protected String endDate; // the endate is set to protected because it can be set to by this class or by the subclass

    // we can have a default constructor to give our subclasses more flexibility
    // constructors can be private in case of singleton desing pattern


    public Worker() {
        // if i dont give this
    }

    public Worker(final String name, final String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName()
    {
        return this.name;
    }

    // three methods

    public int getAge() {
        int currentYear = LocalDate.now().getYear();
        int brithYear = Integer.parseInt(birthdate.substring(6));  // assuming we get the date as dd/mm/yyyy

        return (currentYear - brithYear); // this will give us the current age
    }

    public double collectPay() {
        return 0.0;

    }

    public void terminate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
