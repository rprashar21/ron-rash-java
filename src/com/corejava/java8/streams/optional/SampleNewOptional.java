package com.corejava.java8.streams.optional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class SampleNewOptional {
    public static void main(String[] args) {
        // List will not check for duplicacy
        //set will only check if equals and hascode is applied same with hashmap
        //
//        List<Footballer> footballerList = new ArrayList<>();
        Set<Footballer> footballerList = new HashSet<>();
        Footballer footballer = new Footballer("rohit","man united");
        Footballer footballer1 = null;
        Footballer footballer2 = new Footballer("rohit","man united");
        Optional.ofNullable(footballer).ifPresent(futsal -> footballerList.add(futsal));
        Optional.ofNullable(footballer1).ifPresent(futsal -> footballerList.add(futsal));

        Optional.ofNullable(footballer2).ifPresent(futsal -> footballerList.add(futsal));

        System.out.println(footballerList); // prints only one

        HashMap<Footballer,String> map = new HashMap<>();
        map.put(footballer,"long term");

    }
}
class Footballer{
    String name;
    String club;

    public Footballer(final String name, final String club) {
        this.name = name;
        this.club = club;
    }

    @Override
    public String toString() {
        return "Footballer{" +
                "name='" + name + '\'' +
                ", club='" + club + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object){
        if (this ==object )return true;
        if(this.getClass() != object.getClass() || object==null)
            return false;

        Footballer anotherOject = (Footballer) object;
        return ((this.name.equals(anotherOject.name) && this.club.equals(anotherOject.club)));


    }

    @Override
    public int hashCode(){
        return Objects.hash(this.club,this.name);
    }
}