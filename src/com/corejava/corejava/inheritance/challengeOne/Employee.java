package com.corejava.corejava.inheritance.challengeOne;

public class Employee extends Worker{

    // class is very specific with specific employeee attributes
    // if we have a default constructor we will not get any error ,, because java will create its own default constructor for the empty constructor
    private long employeeId;
    private String hireDate;

    private static int employeeNo=1;

    // the first call is to the super constructor to intilize the data memebers of the parent class
    // static field employeeNo to be shared across all the employees
    //
    public Employee(final String name, final String birthdate,  final String hireDate) {
        super(name, birthdate);
        this.employeeId = Employee.employeeNo++;
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", hireDate='" + hireDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
