package collections.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapsVsConcurrentHashMap extends Thread{

    /*
    * map           vs                           concurrentHashmap
    * single locking                segment,bucket locking
    * non sync                        sync
    * allows null and key values      does not alllow
    * faster                         slower compared to hashmap but faster that hashtable
    * fail-fast                        fail safe
    *
    *  map internaly has buckets of k,v which are nothing but linkedlists/nodes when ther is lot of collision
    * then the structure is a balanced tree ie the node are balanced
    *   Internal working of a hashmap -- map.put(K,V) --> cal the hash of the key() using hashcode()--->
    *    --> if hashing collision -->no--> then simply add that k,V,Hash,nextnode
    *   --> hashing collision -->yes-- the key is checked with equlas method to see if they are same -- if same replaced
    *   if not the object is placed to the next node of the linkedlist
    *
    *   if key is null in hashmap it will be stored in index 0 , if we put another equals method and replaced
    *
    *   TreeMap internall using comparable so thet objects will be sorted
    *
    *
    *
    * */
    static Map<Integer,String> map = new HashMap<>();

    public void run(){
        try {
            Thread.sleep(1000);
            map.put(103, "D");
            System.out.println();
        }
        catch (InterruptedException e)
        {
            System.out.println("Child Thread"+Thread.currentThread().getName());
        }
    }



    public static void main(String[] args) throws InterruptedException{

// main thread
        map.put(100,"A");
        map.put(200,"B");

        MapsVsConcurrentHashMap childThread = new MapsVsConcurrentHashMap();
        childThread.start(); // statrt the child thread and since here we have a shared object map

        // we wil get an exception because the child thread will try to access the objects whihc is held by the another thread
        // resolve this by conurrentHashMap
        Set<Integer> set = map.keySet();
        for(Map.Entry<Integer,String> entry: map.entrySet())
        {
            System.out.println(entry.getKey());

        }
        System.out.println(map);
    }
}

