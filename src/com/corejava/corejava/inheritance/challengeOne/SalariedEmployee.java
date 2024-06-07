package com.corejava.corejava.inheritance.challengeOne;

public class SalariedEmployee extends Employee{
    // name, birthdate , endate ,, methods retire/paystructure -- comes from worker class-- some basic implementation
    // we can change the impl based on the specific class

    // employeedate , hiredate


    // take a ways on inheritance
    // we ovverode a method in worker class and applied a specific implementaion for a specific type of employee
    // we also created a method retire which is not found on employee or worker class bcz thats is again specific to salaried employee
    private double annualSalary;
    private boolean isRetired;

    public SalariedEmployee(final String name, final String birthdate, final String hireDate, final double annualSalary) {
        super(name, birthdate, hireDate);
        this.annualSalary = annualSalary;
    }

    @Override
    public double collectPay() {
        double paycheck = isRetired ? (this.annualSalary * 4 ): annualSalary/12;
       return paycheck;
    }

    public void retire()
    {
        terminate("12/12/2060");
        this.isRetired=true;
    }
}
