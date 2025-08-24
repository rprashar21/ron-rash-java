package com.corejava.corejava.oops.polymorphism;

public class Animal {
    void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    void speak() {
        System.out.println("Dog barks");
    }

    public static void main(String[] args) {
        Animal pet = new Dog();
        pet.speak(); // prints "Dog barks"
    }

    //Why is it "Dynamic"?
    //
    //At compile time, compiler only sees Animal
    //
    //At runtime, JVM checks actual object type → calls Dog.speak()

    //| Example                   | Compile-time Polymorphism                                             | Runtime Polymorphism                                      |
    //| ------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------- |
    //| Banking App: `transfer()` | Overloaded: `transfer(String, double)` vs `transfer(Account, double)` | Overridden: `DomesticTransfer` vs `InternationalTransfer` |
    //| UI Component: `render()`  | Not overloaded                                                        | `Button.render()` vs `Dropdown.render()`                  |
}


