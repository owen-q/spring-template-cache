package com.example.demo;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class CacheableFreeMarkerConfigurer extends FreeMarkerConfigurer {
    @Nullable
    private ViewCacheable viewCacheable;

    @Nullable
    private CacheKeyGenerator cacheKeyGenerator;

    public void setViewCacheable(@Nullable ViewCacheable viewCacheable) {
        this.viewCacheable = viewCacheable;
    }

    public void setCacheKeyGenerator(@Nullable CacheKeyGenerator cacheKeyGenerator) {
        this.cacheKeyGenerator = cacheKeyGenerator;
    }
}
