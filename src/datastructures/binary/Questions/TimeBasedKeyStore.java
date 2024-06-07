package datastructures.binary.Questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeBasedKeyStore {

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("love", "high", 10);
        timeMap.set("love", "low", 20);
        System.out.println(timeMap.get("love", 5));
        System.out.println(timeMap.get("love", 10));
        // timeMap.set("foo", "bar2", 4);

        System.out.println(timeMap.get("love", 15));
        System.out.println(timeMap.get("love", 20));
        System.out.println(timeMap.get("love", 25));

    }
//[[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
    //[null,null,null,"","high","high","low","low"]
}

class TimeMap {

    HashMap<String, List<Pair>> map = new HashMap<>();

    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        if (map.containsKey(key)) {
            List<Pair> pairs = map.get(key);
            pairs.add(new Pair(timestamp, value));
            map.put(key, pairs);
        } else {
            List<Pair> pairs = new ArrayList<>();
            pairs.add(new Pair(timestamp, value));
            map.put(key, pairs);
        }
    }

    public String get(String key, int timestamp) {

        // we need to perform binary search
        //map --> "foo" Pair {timeStamp,value}

        if (!map.containsKey(key))
            return "";
        List<Pair> pairs = map.get(key);

        int index = binarySearchOnData(pairs, timestamp);
        if (index == -1)
            return "";
        return pairs.get(index).value;
    }

    private int binarySearchOnData(List<Pair> list, int target) {

        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int currentTime = list.get(mid).time;
            if (currentTime == target)
                return mid;
            else if (target > currentTime) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        return end;
    }
}


class Pair {
    public Integer time;
    public String value;

    public Pair(final Integer key, final String value) {
        this.time = key;
        this.value = value;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */