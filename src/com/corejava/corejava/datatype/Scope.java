package com.corejava.corejava.datatype;

public class Scope {

    private final String name="rohit"; // global variable
    public static void main(String[] args) {

        Scope scope = new Scope();
        scope.getName();
    }

    public void getName(){
        String name ="swati";
        System.out.println(name); // local variable
        System.out.println(this.name); // global variable belobgs to the class
        name=name+"prashar";// local variable ,, if i wnat to change the global vairable access using this
        System.out.println(name);
    }

}
