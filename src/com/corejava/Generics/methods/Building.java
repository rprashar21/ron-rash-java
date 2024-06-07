package com.corejava.Generics.methods;

public class Building {

  private String name;

    public Building(final String name) {
        System.out.println("name is "+name);
        this.name = name;
    }

    public void paint()
    {
        System.out.println(this.name+" paint in building");
    }
}
class HouseNew extends Building{
    private String name;

    public HouseNew(final String name, final String name1) {
        super(name);
        this.name = name1;
    }
}
