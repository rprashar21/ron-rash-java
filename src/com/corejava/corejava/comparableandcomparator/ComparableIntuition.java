package com.corejava.corejava.comparableandcomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparableIntuition {

    /*
    * Think of Comparable as a self-sorting contract.
It tells Java: “Here’s how to compare ME with another object of my kind.”
* Java has no idea how to compare two players unless you tell it.
* Comparable Interface has one method compareTo(OBject o )
* Use with Collections
    *
    *
    *
    *
    * whereas Comparator -- You want to sort the same object in multiple ways
    * It sits outside the object and defines how two objects should be compared.
    *
    * Comparable = natural/default ordering (defined inside the class itself).
      Comparator = flexible, can define many sorting rules outside the class
      *
      * Suppose u have a players in chess
      * u want to sort based on maybe name or age  or score
      *
      * Defined in	Inside class	Outside class
          Sort type	One fixed way	Multiple custom ways
Method	compareTo(T o)	compare(T o1, T o2)
Use with	Collections.sort()	Collections.sort(list, comparator)
    * */

    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();
        players.add(new Player("Ravi", 25, 80));
        players.add(new Player("Amit", 22, 95));
        players.add(new Player("Kiran", 30, 85));

// Sort by score
        players.sort(new ScoreComparator());
        // or
        players.sort((p1, p2) -> p1.score - p2.score);
        Collections.sort(players, Comparator.comparingInt(p -> p.score));
        System.out.println("Sorted by Score: " + players);


        // Sort by name
        Collections.sort(players, new NameComparators());
        System.out.println("Sorted by Name: " + players);

// Sort by age
        Collections.sort(players, new AgeComparator());
        System.out.println("Sorted by Age: " + players);
    }
}

class Player {
    String name;
    int age;
    int score;

    public Player(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String toString() {
        return name + " - Age: " + age + ", Score: " + score;
    }
}


class ScoreComparator implements Comparator<Player> {
    public int compare(Player a, Player b) {
        return b.score - a.score; // descending
    }
}

class NameComparators implements Comparator<Player> {
    public int compare(Player a, Player b) {
        return a.name.compareTo(b.name); // A to Z
    }
}

class AgeComparator implements Comparator<Player> {
    public int compare(Player a, Player b) {
        return Integer.compare(a.age, b.age); // ascending
    }
}


