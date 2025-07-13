package cache;

import java.util.HashMap;
import java.util.Map;

public class CacheAside {

    public final HashMap<String, Object> cache = new HashMap<>();

    // Simulated DB
    private final Map<String, String> database = Map.of(
            "123", "User: Rohit",
            "456", "User: Swati"
    );

    public Object get(String key) {
        // we will check the cache
        if (cache.containsKey(key)) {
            Object cached = cache.get(key);
            if ("NOT_FOUND".equals(cached)) {
                return "There key is not cached yet";
            }
            return cached;
        }
        //fecth from the db
        String fecthFromDb = database.get(key);

        if (fecthFromDb == null) {
            cache.put(key, "NOT_FOUND");
            return "User not found in DB are u sure this is a valid key";
        }
        // if the value is in the db then put it in the cache and return
        cache.put(key, fecthFromDb);
        return fecthFromDb;
    }

    public void set(String Key, Object value) {
        cache.put(Key, value);
    }

    public static void main(String[] args) {
        CacheAside cacheAside = new CacheAside();

        cacheAside.set("134","User: Jack" );
        System.out.println(cacheAside.get("100")); // Miss → DB null → NOT_FOUND cache
        System.out.println(cacheAside.get("100")); // Cache hit for NOT_FOUND
    }
}
//Use libraries like Caffeine, Redis, or Spring Cache with custom null-handling logic.
//Add TTL for NOT_FOUND to avoid long-term cache pollution.
//You can also rate limit requests with frequent bad keys.
