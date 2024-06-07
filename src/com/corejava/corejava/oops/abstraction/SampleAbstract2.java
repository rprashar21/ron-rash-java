package com.corejava.corejava.oops.abstraction;


import java.util.ArrayList;

import datastructures.linkedlists.implementation.singleLinkedList.List;

abstract class Animals{

    protected String type; // subclasses can access type directly without getters
    private final String size;
    public static final boolean canFly= Boolean.FALSE;
    private double wieght;

    public Animals(final String type, final String size, final double wieght) {
        this.type = type;
        this.size = size;
        this.wieght = wieght;
    }

    // abstract methods
    public abstract void move(String speed);
    public abstract void makeNoise();
}

class Doggie extends Animals{

    public Doggie(final String type, final String size, final double wieght) {
        super(type, size, wieght);
    }

    @Override
    public void move(final String speed) {
        System.out.println(type+" runs at a speed of "+speed);
    }

    @Override
    public void makeNoise() {
    if(type.equals("WOLf"))// directlt access from parent class
    {
        System.out.println("wooooooooolff");
    }
    else if(type.equals("fox")){
        System.out.println("fooooooooo");
    }
    else {
        System.out.println("bow bow");
    }
    }

    public boolean canFly()
    {
       return Animals.canFly;
    }
}
class Fish extends Animals{
    public Fish(final String type, final String size, final double wieght) {
        super(type, size, wieght);
    }

    @Override
    public void move(final String speed) {
        System.out.println(type+" swims at a speed of "+speed);
    }

    @Override
    public void makeNoise() {
        if(type.equals("jelly"))// directlt access from parent class
        {
            System.out.println("swishhhhhh");
        }
        else if(type.equals("gold")){
            System.out.println("splash");
        }

    }

    public boolean canFly() {
      return   Animals.canFly;
    }
}

public class SampleAbstract2 {

    public static void main(String[] args) {

        // we cannot create an instance of an animal becuase its abstract

        Doggie dog  = new Doggie("fox","large",20.0);

        Fish fish = new Fish("jelly","smalll",10.0);
        doAnimalStuff(fish);
        doAnimalStuff(dog);

        // now we can create aan ArraylIst of Animals
        ArrayList<Animals> animalsList = new ArrayList<>();

        // here we can pass an array of animals like dog fish and they have same charateristics but different functionality
        animalsList.add(new Doggie("German","large",90));
        animalsList.add(new Fish("gold","large",190));
        animalsList.add(new Doggie("German ","large",90));

        animalsList.forEach(animals ->
        {
            System.out.println("Inside "+animals.type);
            animals.makeNoise();});
    }

    private static void doAnimalStuff(Animals animals)
    {
        // here to the outside world we can pass any object of type animal and do animal stuff
        animals.makeNoise();
        animals.move("20kmph");
    }
}