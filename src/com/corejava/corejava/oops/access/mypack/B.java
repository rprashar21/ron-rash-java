package com.corejava.corejava.oops.access.mypack;

import com.corejava.corejava.oops.access.pack.A;

public class B extends A {
    public static void main(String[] args) {

        B b = new B();
        b.hello(); // im able to access method of A class which is in diff package becoz the access modifier is proteected

        // if i remove the access modifier to default i will not be able to access the method
    }
}
// File: MyClass.java in package1
// package package1;

class MyClass { // Default access specifier for class
    void display() { // Default access specifier for method
        System.out.println("Hello from MyClass in package1");
    }
}
// File: Main.java in package1
//package package1;

//public class Main {
//    public static void main(String[] args) {
//        MyClass obj = new MyClass(); // Accessible within the same package
//        obj.display(); // Accessible method
//    }
//}

// File: Test.java in package2
//package package2;
//
//        import package1.MyClass;
//
//public class Test {
//    public static void main(String[] args) {
//        MyClass obj = new MyClass(); // Compilation Error: MyClass not visible
//        obj.display(); // Compilation Error: display() not visible
//    }
//}
