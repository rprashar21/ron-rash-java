package com.corejava.corejava.staticsample;

import java.util.HashMap;

public class SingeltonExample {

    private HashMap<String,Object> map = new HashMap<>();

    private static final SingeltonExample instance= new SingeltonExample();

    public static SingeltonExample getInstance(){
        return instance;
    }

    public void put(String key,Object obj){
        // caching logic
        map.put(key,obj);
    }

    public Object get(String key){
        if(map.containsKey(key)){
            return "Key is already present";
        }
        return map.get(key);
    }

    public static void main(String[] args) {
        SingeltonExample obj = SingeltonExample.getInstance();
        obj.put("rohit","Swati");

        SingeltonExample obj1 = SingeltonExample.getInstance();
        obj1.put("rohit","united");

        System.out.println(obj.get("rohit"));


    }
}
