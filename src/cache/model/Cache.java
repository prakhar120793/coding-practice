package cache.model;

import javafx.util.Pair;

import java.util.Optional;

public interface Cache {
    //only when evicted it would return otherwise option is empty.
    Optional<Pair<String, CacheEntry>> putInCache(String cacheKey, CacheEntry cacheEntry);

    CacheEntry getFromCache(String cacheKey) throws LRUCache.CacheEntryNotFound;
}
