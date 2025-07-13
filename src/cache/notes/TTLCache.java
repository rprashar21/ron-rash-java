package cache.notes;

import java.util.HashMap;
import java.util.Map;

public class TTLCache<K,V> {

    // time based caching -- evicy items based n a certain time

    class CacheValue {
        V value;
        long expiryTime;
        CacheValue(V v, long ttlMs) {
            value = v;
            expiryTime = System.currentTimeMillis() + ttlMs;
        }
    }

    private final Map<K, CacheValue> cache = new HashMap<>();
    private final long ttl;

    public TTLCache(long ttlMillis) {
        this.ttl = ttlMillis;
    }

    public void put(K key, V value) {
        cache.put(key, new CacheValue(value, ttl));
    }

    public V get(K key) {
        CacheValue cv = cache.get(key);
        if (cv == null || System.currentTimeMillis() > cv.expiryTime) {
            cache.remove(key);
            return null;
        }
        return cv.value;
    }
}


