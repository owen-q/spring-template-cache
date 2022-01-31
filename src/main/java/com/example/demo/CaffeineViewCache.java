package com.example.demo;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CaffeineViewCache implements ViewCacheable {
    private final Cache<String, String> loadingCache;

    public CaffeineViewCache() {
        // TODO: configurable
        this.loadingCache = Caffeine.newBuilder()
                                    .expireAfterWrite(5L, TimeUnit.SECONDS)
                                    .build();
    }

    @Override
    public String get(String key) {
        return this.loadingCache.getIfPresent(key);
    }

    @Override
    public void set(String key, String content) {
        this.loadingCache.put(key, content);
    }
}
