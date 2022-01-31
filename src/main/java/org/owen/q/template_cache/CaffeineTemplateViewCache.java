package org.owen.q.template_cache;

import java.util.Objects;

import org.owen.q.template_cache.cache.TemplateViewCacheable;
import org.owen.q.template_cache.config.TemplateViewCacheOptions;
import org.springframework.util.Assert;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CaffeineTemplateViewCache implements TemplateViewCacheable {
    private final Cache<String, String> loadingCache;

    public CaffeineTemplateViewCache(TemplateViewCacheOptions configuration) {
        Assert.notNull(configuration.getExpireAfterWriteDuration(), "configuration.expireAfterWriteDuration must not null");
        Assert.notNull(configuration.getExpireAfterWriteDurationUnit(), "configuration.expireAfterWriteDurationUnit must not null");

        Caffeine builder = Caffeine.newBuilder()
                                    .expireAfterWrite(configuration.getExpireAfterWriteDuration(), configuration.getExpireAfterWriteDurationUnit());

        if (Objects.nonNull(configuration.getMaximumSize())) {
            builder.maximumSize(configuration.getMaximumSize());
        }

        if (Objects.nonNull(configuration.getCustomExecutor())) {
            builder.executor(configuration.getCustomExecutor());
        }

        this.loadingCache = builder.build();
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