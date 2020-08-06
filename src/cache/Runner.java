package cache;

import cache.model.LRUCache;

public class Runner {
    public static void main(String[] args) {
        SystemCache systemCache =
                new SystemCache(LRUCache.createCache(100), LRUCache.createCache(200), LRUCache.createCache(300));


    }
}
