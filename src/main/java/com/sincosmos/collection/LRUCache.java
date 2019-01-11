package com.sincosmos.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache {
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<Integer,Integer>((int)(Math.ceil(capacity/0.75f) + 1), 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
                return size() > capacity;
            }
        };
    }

    public int get(int key){
        return Optional.ofNullable(cache.get(key)).orElse(-1);
    }

    public void put(int key, int value){
        cache.put(key, value);
    }
}
