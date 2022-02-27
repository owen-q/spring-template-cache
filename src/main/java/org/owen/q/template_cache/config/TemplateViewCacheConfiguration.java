package org.owen.q.template_cache.config;

import org.owen.q.template_cache.cache.impl.TemplateCacheDefaultKeyGenerator;
import org.owen.q.template_cache.core.CacheableViewRenderer;
import org.owen.q.template_cache.core.TemplateCacheFinalizer;
import org.owen.q.template_cache.core.TemplateCacheKeyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateViewCacheConfiguration {
//    @Bean
//    public RequestScopeTemplateViewCacheMetaData requestScopeCacheMetaData() {
//        return new RequestScopeTemplateViewCacheMetaData();
//    }

    @Bean
    public TemplateCacheFinalizer templateCacheFinalizer() {
        return new TemplateCacheFinalizer();
    }

    @Bean
    public CacheableViewRenderer cacheableViewRenderer() {
        return new CacheableViewRenderer();
    }

//    @ConditionalOnMissingBean(value = { TemplateViewCacheable.class })
//    @Bean
//    public TemplateViewCacheable viewCacheable(TemplateViewCacheOptions configuration) {
//        return new CaffeineTemplateViewCache(configuration);
//    }

    @Bean
    public TemplateCacheDefaultKeyGenerator templateCacheDefaultKeyGenerator() {
        return new TemplateCacheDefaultKeyGenerator();
    }

    @Bean
    public TemplateCacheKeyBuilder templateCacheKeyBuilder() {
        return new TemplateCacheKeyBuilder();
    }
}
