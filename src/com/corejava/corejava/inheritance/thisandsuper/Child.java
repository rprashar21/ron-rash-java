package com.corejava.corejava.inheritance.thisandsuper;

public class Child extends Parent{


    private String name;
    private String address;
    private String email;
    public Child() {
        super();
        System.out.println("default constructor of child is called");
    }

    public Child(final String name) {
//        super();
        this(); // can use only one either this or super
        this.name = name;

        /*
        * this is used to call a constructor and must be the first statement in a constructor
        * helps to call overloaded constructor used for constructor chaining -- reduces duplicatoe code
        *
        * */

    }

    public Child(final String name, final String address) {
        this(name);
        this.address = address;
    }

    public void childMethod()
    {
        System.out.println("Inside child");
        super.printParent();

    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }
}
