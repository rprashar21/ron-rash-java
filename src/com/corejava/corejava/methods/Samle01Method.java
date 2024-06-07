package com.corejava.corejava.methods;

public class Samle01Method {

    public static void main(String[] args) {
      toMilesPerHour(25.42);
    }

    public static long toMilesPerHour(double kilometersPerHour){

        if(kilometersPerHour < 0)
            return -1;

        // convert km to toMiles
        double rounded =  kilometersPerHour/1.5;
        long round =(long) Math.ceil(rounded);
        System.out.println(round);
        return round;
    }
}
