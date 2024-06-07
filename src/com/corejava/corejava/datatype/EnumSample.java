package com.corejava.corejava.datatype;

enum Items{
    // when we want a group of constant or array of constants  like days of the week months planets
    // they are fixed - we can define our own datatypes
    // biscaly every enum constant is a public static final and is of type enum hence no inheritance bu can implement interface
    // public static final EnumSample MONDAY = new EnumSample;
    // every enum is an object of type enum
    // inside enum tostring method is implemented that is why it directly returns the name of the class--


    // we can also have constructors fields and methods for enums
    RICE(100),
    CHEESE(20),
    TOMATO;

    int price;

    Items() {
        // this is important for Tomato class or else we will get compile time error
        this.price=2;
    }

    Items(final int price) {
        this.price = price;
    }

    // can have a method as well
    public int getPrice()
    {
        return this.price;
    }
}
public class EnumSample {

    public static void main(String[] args) {
        // we cannot use the new keyword

        Items[] items = Items.values();
        System.out.println(Items.CHEESE.name()); // CHEESE
        System.out.println(Items.CHEESE.getPrice());
        for(Items item:items)
        {
            System.out.println(item.name()+" "+item.getPrice());
        }
    }


}

