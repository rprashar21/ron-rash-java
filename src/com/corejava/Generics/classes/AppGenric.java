package com.corejava.Generics.classes;

public class AppGenric {

    public static void main(String[] args) {
        CustomGenericArrayList<Integer> customArrayList = new CustomGenericArrayList();

        customArrayList.add(10);
        customArrayList.add(20);
        // here i cannot add a string but i can add at the top to restict that use a wild card
        customArrayList.add(30);
        customArrayList.remove();

        System.out.println(customArrayList);


    }

}
