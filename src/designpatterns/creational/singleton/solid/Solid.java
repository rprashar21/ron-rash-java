package designpatterns.creational.singleton.solid;

public class Solid {
    // a class should have only one responsibility
    //
    /*
    * SOLID --
    *  1. S-> Single responsiblity principle
    *  2. O > Open for extension but closed for modification
    * NotificationService -- sendOTp()  -- make it an interface or abstract class and
    * then have other classes implement this and have ots own methods
    *  have diff
    *
    * 3.Liskov Substituion Principle > It applies to inheritance in such a way that the derived classes must
    *   be completelty substituable for their base classes.In other words ,if a class A is a subtype of class B
    *   then we should be able to sunstitue B with A without interuppting the behaviour of A.
    * 4.Interface Segregation Principle - this principle states that larger interfaces should be split into smaller interfaces
    *       bcz the implementation classes use only methods that are required. we should not force clients to use methods that they do not wnat to use
    *       its is very similar to srp.
    * 5. Dependency Inversion Principle - The principal states that we must use abstraction
    *        (abstract classes and interfaces) instead of concrete implementations
    *        High level modules should not depend upon low level modules but both should depend upon the abstraction.
    *
    * */


    public static void main(String[] args) {

          // userdetails -- usercontroller -- userservice -- table
        // bank account -- pojo -- bankid -- accnt number ,name phone
    }
}
