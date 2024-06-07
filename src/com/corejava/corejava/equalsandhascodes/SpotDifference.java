package com.corejava.corejava.equalsandhascodes;

import java.util.Objects;

public class SpotDifference {

    public static void main(String[] args) {

        // without equals and hashcode
        WithoutEqualsAndHashCode obj1 = new WithoutEqualsAndHashCode(1, "dog");
        WithoutEqualsAndHashCode obj2 = new WithoutEqualsAndHashCode(1, "dog");
        WithoutEqualsAndHashCode obj3 = new WithoutEqualsAndHashCode(1, "dog");

        System.out.println(obj1.hashCode() + " " + obj2.hashCode() + " " + obj3.hashCode());

        System.out.println(obj1 == obj2); // false
        System.out.println(obj1.getName().equals(obj2.getName())); // true

        WithEqualsAndHashCode obj4 = new WithEqualsAndHashCode(1,"dog");
        WithEqualsAndHashCode obj5 = new WithEqualsAndHashCode(1,"dog");
        WithEqualsAndHashCode obj6 = new WithEqualsAndHashCode(1,"cat");
        WithEqualsAndHashCode obj7 = obj4;
        System.out.println(obj4==obj5);// false this checks refernce and not hascode
        System.out.println(obj4.hashCode()+" "+obj5.hashCode());
        System.out.println(obj4.equals(obj5)); // true // by equals method they are same becoz they have same contents
        System.out.println(obj4.equals(obj6)); // false

        System.out.println(obj7==obj5); // false
        System.out.println(obj7.equals(obj5)); // true
        System.out.println(obj7==(obj4)); //true bth are pointing to same object

    }


}

class WithoutEqualsAndHashCode {

    private int id;
    private String name;

    public WithoutEqualsAndHashCode(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class WithEqualsAndHashCode {

    private int id;
    private String name;

    public WithEqualsAndHashCode(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else if (obj == null || this.getClass() != obj.getClass())
            return false;
        else {
            WithEqualsAndHashCode another = (WithEqualsAndHashCode) obj;
            return this.id == another.id && Objects.equals(this.name, another.name);
        }

    }

    public int hashCode() {
        return Objects.hash(id, name);
    }
}