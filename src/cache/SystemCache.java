package cache;

import cache.model.Cache;
import cache.model.CacheEntry;

public class SystemCache {

    private final Cache l1Cache;
    private final Cache l2Cache;
    private final Cache l3Cache;


    public SystemCache(Cache l1Cache, Cache l2Cache, Cache l3Cache) {
        this.l1Cache = l1Cache;
        this.l2Cache = l2Cache;
        this.l3Cache = l3Cache;
    }

    public void putInCache(String cacheKey, CacheEntry cacheEntry) {
        long startTime = System.nanoTime();
        {

        }
        long endTime = System.nanoTime();
    }

    public void getFromCache(String cacheKey) {

    }
}
