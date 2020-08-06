package cache.model;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache implements Cache {

    private final Map<String, CacheEntry> cacheEntryMap;
    private final Queue<String> cacheKeyQueue;
    private final int maxCacheSize;

    //factory method
    public static LRUCache createCache(int maxCacheSize) {
        return new LRUCache(new ConcurrentHashMap<>(), new LinkedList<>(), maxCacheSize);
    }

    protected LRUCache(Map<String, CacheEntry> cacheEntryMap, Queue<String> cacheKeyQueue,
            int maxCacheSize) {
        this.cacheEntryMap = cacheEntryMap;
        this.cacheKeyQueue = cacheKeyQueue;
        this.maxCacheSize = maxCacheSize;
    }

    @Override
    public synchronized Optional<Pair<String, CacheEntry>> putInCache(String cacheKey, CacheEntry cacheEntry) {
        Optional<Pair<String, CacheEntry>> optionalEvictedEntry = Optional.empty();
        if (isCacheFull()) {
            //evict
            String keyToBeRemoved = this.cacheKeyQueue.remove();
            CacheEntry evictedEntry = this.cacheEntryMap.remove(keyToBeRemoved);
            optionalEvictedEntry = Optional.of(new Pair<>(keyToBeRemoved, evictedEntry));
        }

        //put in cache
        this.cacheKeyQueue.add(cacheKey);
        this.cacheEntryMap.put(cacheKey, cacheEntry);

        return optionalEvictedEntry;
    }

    private boolean isCacheFull() {
        return this.cacheKeyQueue.size() == this.maxCacheSize;
    }

    @Override
    public CacheEntry getFromCache(String cacheKey) throws CacheEntryNotFound {
        if (this.cacheEntryMap.containsKey(cacheKey)) {
            return this.cacheEntryMap.get(cacheKey);
        }
        throw new CacheEntryNotFound();
    }

    public static class CacheEntryNotFound extends Exception {

    }
}
