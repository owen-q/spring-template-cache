package org.owen.q.template_cache.config;

import org.owen.q.template_cache.CacheableViewRenderer;
import org.owen.q.template_cache.TemplateCacheFinalizer;
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
}
