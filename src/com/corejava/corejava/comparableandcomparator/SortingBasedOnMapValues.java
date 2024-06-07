package com.corejava.corejava.comparableandcomparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

public class SortingBasedOnMapValues {
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("ron", 21);
        map.put("sheena", 18);
        map.put("ajay", 23);
        map.put("vijay", 25);
        map.put("bob", 12);

        // default sorting based on keys ie names
        //  String class implements the Comparable Interface hence it is best option fo maps to use it as keys
        // also they are immutable
        TreeMap<String, Integer> keySortMap = new TreeMap<>(map);
        System.out.println(keySortMap);// {ajay=23, bob=12, ron=21, sheena=18, vijay=25}


        //the treemap takes in a comparator hence we can use our own default sorting ie we can use Comparator interface  compare method to sort values based on our need
        // sorting age in decreasing manner
        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                return s2.compareTo(s1);
            }
        };

        TreeMap<String, Integer> newTreeMap = new TreeMap<>(stringComparator);
        newTreeMap.putAll(map);
        System.out.println("latest tree map "+newTreeMap);
        //  Java 8
        TreeMap<String, Integer> valueSortMap = new TreeMap<>((v1, v2) -> map.get(v2).compareTo(map.get(v1)));

        valueSortMap.putAll(map);
        System.out.println(valueSortMap);// {vijay=25, ajay=23, ron=21, sheena=18, bob=12}

        // sorting age in increasing manner
        // TreeMap<String, Integer> valueSortMap2 = new TreeMap<>((v1, v2) -> map.get(v1).compareTo(map.get(v2)));
        TreeMap<String, Integer> valueSortMap2 = new TreeMap<>(Comparator.comparing(map::get));
        valueSortMap2.putAll(map);
        System.out.println(valueSortMap2);// {bob=12, sheena=18, ron=21, ajay=23, vijay=25}

        // Before Java 8
        class AgeComparator implements Comparator {

            @Override
            public int compare(final Object o1, final Object o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        }
        TreeMap<String, Integer> integerTreeMap = new TreeMap<>(new AgeComparator());
        integerTreeMap.putAll(map);
        System.out.println(integerTreeMap);

        HashMap<Driver,String> driverMap = new HashMap<>();
        // sort the driverMap based on driver id
        driverMap.put(new Driver("rohit",21),"footballer");
        driverMap.put(new Driver("swati",20),"singer");
        driverMap.put(new Driver("shahbaz",22),"footballer");
        driverMap.put(new Driver("sadaf",19),"designer");

        TreeMap<Driver,String> sortedDriverMapBasedOnTheirIds = new TreeMap<>((driver1,driver2)->Integer.compare(driver2.getId(),driver1.getId()));
        sortedDriverMapBasedOnTheirIds.putAll(driverMap);

        System.out.println(" descending driver map values sorted based on theeir ids "+sortedDriverMapBasedOnTheirIds);

        TreeMap<Driver,String> sortedDriverMapBasedOnTheirNames = new TreeMap<>((driver1,driver2)->driver2.getName().compareTo(driver1.getName()));
        sortedDriverMapBasedOnTheirNames.putAll(driverMap);

        System.out.println(" descending driver map values sorted based on theeir names  "+sortedDriverMapBasedOnTheirNames);
    }
}

class Driver{
    private String name;
    private Integer id;

    public Driver(final String name, final Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(id, driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}