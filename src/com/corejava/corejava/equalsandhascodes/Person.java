package com.corejava.corejava.equalsandhascodes;

import java.util.Objects;

public class Person {
    int id;
    String name;

    public Person(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        // what is a hashcode?
        // hascode genereate ann integer value ,return the hashcode of the object
        //This method is supported for the benefit of hash tables such as those provided by HashMap.
       // f two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same integer result.
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
