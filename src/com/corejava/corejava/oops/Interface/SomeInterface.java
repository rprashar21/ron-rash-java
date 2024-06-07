package com.corejava.corejava.oops.Interface;

public interface SomeInterface {

    public int add();

    public int sub();

    static int getValue()
    {
        return 300;
    }
    default int getAdd()
    {
        return 800;
    }
}
 interface SomeInterfaceNew {


    default void getAdd()
    {
        System.out.println("default method");
    }
}

// this class can be abstarct
abstract class SomeClass implements SomeInterface {

    // cannot provide weaker access privileges.
    @Override
    public int add() {
        return 0;
    }
    // abstarct class is not responsible to provide implementation for the interface
}
// the child class is now responsble for implementation for the interface
abstract class SomeChildClass extends SomeClass{

//    public int sub() {
//        return 0;
//    }
}

class Child extends SomeChildClass{

    @Override
    public int sub() {
        return 0;
    }

    @Override
    public int add()
    {
        return 9;
    }
}

class AnotherChild implements SomeInterfaceNew{

}
