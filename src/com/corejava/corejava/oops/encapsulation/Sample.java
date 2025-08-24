package com.corejava.corejava.oops.encapsulation;

public class Sample {
    // Encapsulation in Java is a process of wrapping code and data together into a single unit
    // security data hiding , better for unit testing
//    This approach hides the internal state of an object and protects
//    it from direct modification from outside the class, which is known as data hiding.
}

//ou never want other classes to directly change balance.
class BankAccount {
    private double balance; // ur hidung the internal state -- making it orivate -- only allow changes via deposit()/withdraw(

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
// without encapsulation
 class BadAccount {
    public double balance; // anyone can set it to a negative number!
}

