package com.corejava.corejava.escapingRefernces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApp {

    public static void main(String[] args) {

        // mutable map
     Map<String,String> map = new HashMap<>();
     map.put("rohit","football");
     map.put("swati","shopping");

     map.put("shahbaz","football");

     map.forEach((key,value)->{
         System.out.println(key+" "+value);
     });

        Map<String,String> map2 = new HashMap<>();
        map2=map; // both the maps are pointing to the same map object in heap

     // now it iwll be a immutable map
        Map<String, String> stringStringMap = Collections.unmodifiableMap(map);

        stringStringMap.put("shahbaz","football");
    }
}
