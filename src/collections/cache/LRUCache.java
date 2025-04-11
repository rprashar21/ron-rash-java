package collections.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int maxSize;

    public LRUCache(final int maxSize) {
        super(maxSize, 0.75f, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize; // Evict oldest
    }


    public static void main(String[] args) {
        CustomLRUCache<String,String> cache = new CustomLRUCache<>(3);
        cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.put("C", "Carrot");

        System.out.println(cache.get("C"));

        cache.put("D", "Dog");

        cache.put("E", "Egg");

        System.out.println(cache);

    }
}
