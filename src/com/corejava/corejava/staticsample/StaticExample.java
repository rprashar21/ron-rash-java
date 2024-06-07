package com.corejava.corejava.staticsample;

public class StaticExample {

    static int staticCounter =0;
    int instanceCounter =0;

    public StaticExample() {
        staticCounter++;
        this.instanceCounter = instanceCounter;
    }

    static int getStaticCounter(){
        // form this static method Iam not able to access the instance variable or method
        // im only able to access the static variable and method
        return staticCounter;
    }
    public int getInstanceCounter(){
        staticCounter++;
        return this.instanceCounter;
    }

    public static void main(String[] args) {
        StaticExample s1 = new StaticExample();
        StaticExample s2 = new StaticExample();
        System.out.println(s1.instanceCounter); //0
        System.out.println(StaticExample.getStaticCounter()); //2

        System.out.println(s2.instanceCounter); // 0
        System.out.println(StaticExample.getStaticCounter()); //2

        System.out.println(s1.getInstanceCounter());
        System.out.println(staticCounter);
    }
}

//    In Java, the keyword static is used to denote that a particular member (variable or method) belongs to the class itself,
//    rather than to instances of the class. Here are some key points about static in Java:
//
//        Static Variables: Also known as class variables.
//        These are shared by all instances of the class.
//        If you change the value of a static variable in one instance, it changes for all instances.
//
//        Static Methods: These can be called without creating an instance of the class.
//        They can only directly access static variables and other static methods.
//        They cannot access instance variables or instance methods directly.
//
//        Static Blocks: Used for static initializations of a class.
//        This code inside static blocks is executed only once: the first time the class is loaded into memory.
//
//        Static Classes: Nested classes can be static.
//        A static nested class is associated with its outer class,
//        and like static methods and variables, it can be accessed without creating an instance of the outer class.
//

// When to Use
// Static Varibale -- when the value of the variable has to be common in all the instnaces like college

// static methods -- utility methods -- Math.max
// static blocks --> When to Use: For initializing static variables. This block gets executed exactly once when the class is first loaded.
//Why: Useful for complex static variable initialization or when initialization requires some logic, such as error handling.


// considerations for static
//Overuse is Risky: Excessive use of static members can lead to
// issues with testing and code flexibility.
// They can't be overridden in subclasses, which may limit object-oriented design and polymorphism.
//        Thread Safety: Static variables are not thread-safe unless properly synchronized, as they are shared among all threads running in the JVM.
//        Design Limitations: Static methods cannot access instance variables or methods; they can only access static variables or other static methods.
class Student{
    private String name;
    public static String collegeName = "ABC";

    public String getName() {
        return name;
    }


    public Student(final String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Student obj1 = new Student("rohit");
        Student obj2 = new Student("swati");


        System.out.println(obj1.getName());
        System.out.println(Student.collegeName);
    }
}