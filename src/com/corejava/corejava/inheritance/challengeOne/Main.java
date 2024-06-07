package com.corejava.corejava.inheritance.challengeOne;

public class Main {

    public static void main(String[] args) {
        Employee employee = new Employee("Tim","21/01/1990","23/01/2014");

        SalariedEmployee salariedEmployee= new SalariedEmployee("Jim","21/01/1990","23/01/2014",96000);
        System.out.println(salariedEmployee.getName()+" Age is "+salariedEmployee.getAge());
        System.out.println("monthly salary is "+ salariedEmployee.collectPay()+" £");

        // after i retired jim
        salariedEmployee.retire();
        System.out.println("after retirement he ill get "+ salariedEmployee.collectPay()+ "£");
        System.out.println(salariedEmployee);

        // lets create a hourlemployee

        HourlyEmployee hourlyEmployee = new HourlyEmployee("Tom","21/01/1999","23/01/2014",100);


        // lets see his age // worker class implementation bcz thats common right
        System.out.println(hourlyEmployee.getName()+" Age is "+hourlyEmployee.getAge());
        System.out.println("Hourly pay is "+ hourlyEmployee.collectPay());

    }
}
