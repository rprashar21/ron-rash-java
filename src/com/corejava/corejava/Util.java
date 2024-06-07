package com.corejava.corejava;

import java.util.List;

public class Util {

    public static <T> void sortList(List<T> list) {
        list.forEach(t -> {
            System.out.print(t+" ");
        });
    }
}
