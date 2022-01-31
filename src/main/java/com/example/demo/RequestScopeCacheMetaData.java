package com.example.demo;

public class RequestScopeCacheMetaData extends ThreadLocal<CacheMetadata> {

    @Override
    protected CacheMetadata initialValue() {
        return new CacheMetadata();
    }
}
