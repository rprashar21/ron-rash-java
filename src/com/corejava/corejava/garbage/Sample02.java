package com.corejava.corejava.garbage;

public class Sample02 {

    // garabge collection is the process to free up memory not under developers control
    // although we could call up System.gc --> but this is a suggestion and the jvm may or may not use it
    // objects on the heap which cannot be referenced from stack are eligible for garbage collection
    // Java Virtual Machine (JVM) has a built-in Garbage Collector (GC) that is responsible for this process.
    // marks the objects live and and unreachable objects and slowly removes them from the garbage collector

    // if we have a finalize method -- and inside that we right the system.gc --> we never know when that is going to run if it runs
    // hence it has been removed from java 9

    public static void main(String[] args) {

    }
}
