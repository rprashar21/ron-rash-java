package com.corejava.Generics.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildCards {
    /*
        Wild card shoudl be used with collecion
    *   We know that Object is the supertype of all Java classes. However, a collection of Object is not the supertype of any collection.
    *   List<Object> is not  a superType for List<String>

    *    If we imagine a subtype of Building, such as a House, we can't use this method with a list of House, even though House is a subtype of Building.
         If we need to use this method with type Building and all its subtypes, the bounded wildcard can do the magic:
    *
    * */

//    public static void paintAllBuildings(List<Building> buildings) {
//        buildings.forEach(Building::paint);
//    }

    public static void paintAllBuildings(List<? extends Building> buildings) {
        buildings.forEach(Building::paint);
    }

    public static void getList(List<Number> numberList){
        // here in this method we cannot pass a List<Integer> we can only pass List<NUmber>
    }

    public static void main(String[] args) {

        // for getList method
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        WildCards.getList(numberList); // this is correct
       // WildCards.getList(integerList); // this is wrong and will give comile time error
       // to correct this use another
        Building building = new Building("Green");
        Building building1 = new Building("Blue");

        House yellowHouse = new House("yellow");
        House redHouse = new House("red");
        final List<Building> list = Arrays.asList(building, building1);
        final List<House> houseList = Arrays.asList(yellowHouse, redHouse);
        WildCards.paintAllBuildings(list);

//        If we imagine a subtype of Building, such as a House, we can't use this method with a list of House, even though House is a subtype of Building.
//
//        If we need to use this method with type Building and all its subtypes, the bounded wildcard can do the magic:

     //   WildCards.paintAllBuildings(houseList);
      //  for this to work we need to use generics wildcard

        WildCards.paintAllBuildings(houseList);

//        Now this method will work with type Building and all its subtypes. This is called an upper-bounded wildcard, where type Building is the upper bound.
//
//        We can also specify wildcards with a lower bound, where the unknown type has to be a supertype of
//        the specified type. Lower bounds can be specified using the super keyword followed by the specific type.
//        For example, <? super T> means unknown type that is a superclass of T (= T and all its parents).
    }
}
