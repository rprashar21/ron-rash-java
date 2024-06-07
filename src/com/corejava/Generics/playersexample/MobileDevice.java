package com.corejava.Generics.playersexample;

public class MobileDevice<T> {
    private  T os;






    public static void main(String[] args) {
        MobileDevice<String> mobileDevice1 = new MobileDevice<>();

        MobileDevice<Integer> mobileDevice2 = new MobileDevice<>();
    }
}
