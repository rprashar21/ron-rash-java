package collections.maps.HashMapSample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMapMethods {
    public static void main(String[] args) {


        HashMap<String,Integer> map = new HashMap<>();
        map.put("Apple", 50);
        map.put("Banana", 30);
        map.put("Cherry", 20);

        // iterat thru the map

        for(Map.Entry<String,Integer> entrySet : map.entrySet()){
            System.out.println("key :: "+ entrySet.getKey() + "value :: " + entrySet.getValue());
        }

        // keys should be unique if object should ovveride the equals and hashcode methods
        Set<String> keySet = map.keySet();
        Collection<Integer> values = map.values();

        for(Integer element : values)
        {
            System.out.println(element);
        }

        // put if and getorDefault
        System.out.println(map.getOrDefault("cherry",100));

        map.putIfAbsent("Cherry",20);
        map.putIfAbsent("Orange",200);
//        map.
//        System.out.println(map);


        Map<Character, List<String>> map1 = new HashMap<>();

        // suppose u have football cricket groups and u have to add people to that group
        // u have a list of players
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry","Orange", "bamboo","cucumber");
        // bsaically an immutbale list -- structurall this list cannot be modified

        List<String> immutableList = List.copyOf(fruits);
        // now if we try to add anything in this list it will fail -->

        List.of(fruits).forEach(fruit -> {});
        // i am creating agroups based on alphbets

        for(String fruit : fruits){

            char firstLetter = fruit.toLowerCase().charAt(0);

            // now for each letter it will create a group
            // If groups already has a list for firstLetter, you get it back.
           // If not, the lambda k -> new ArrayList<>() creates and inserts a new ArrayList, and that new list is returned.
            // Finally, .add(word) appends the current word to that list.
            map1.computeIfAbsent(firstLetter,k-> new ArrayList<>()).add(fruit);
       // else we wul have to do the below cod
//
//            if(!map1.containsKey(firstLetter)){
//                List<String> newList = new ArrayList<>();
//                newList.add(fruit);
//                map1.put(firstLetter, newList);
//            }
//            else {
//                List<String> strings = map1.get(firstLetter);
//                strings.add(fruit);
//                map1.put(firstLetter, strings);
//            }
           // map1.get(firstLetter).add(fruit);

            // further simplify this code
           // in interview writh like this or computeIFbasent
//          if(!map1.containsKey(firstLetter)){
//              map1.put(firstLetter,new ArrayList<>());
//          }
//          map1.get(firstLetter).add(fruit);
        }
        System.out.println(map1);

        // anoother example of computeifPresent

        // its bascially check if the key is present in the map and does thing on  the value it self
        // if u look at this

        // suppose uou have a map
        HashMap<String,Integer> scores = new HashMap<>();
        List<String> players = Arrays.asList("Rohit","Shahbaz","Uday","Baba","Sanjiv");

        // lets give them some scores
        scores.put("Shahbaz",90);
        scores.put("Uday",40);
        scores.put("Baba",100);

        // merge adds or does something with an existing value
        scores.merge("Uday",50,Integer::max);

        System.out.println(scores);
        // lets add extra marks to pepeole in the map

        for(String player : players){

            // basically if the value present in the map add 15 to the score
            scores.computeIfPresent(player,(p,score)->score+15);
            // basically if the value is not present in the map set default value of  100
            scores.computeIfAbsent(player,k-> 100);
        }

        System.out.println(scores);
    }







}
