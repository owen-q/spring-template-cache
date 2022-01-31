package org.owen.q.template_cache.demo;

import java.util.concurrent.TimeUnit;

import org.owen.q.template_cache.CacheableFreeMarkerViewResolver;
import org.owen.q.template_cache.CaffeineTemplateViewCache;
import org.owen.q.template_cache.cache.TemplateViewCacheable;
import org.owen.q.template_cache.config.TemplateViewCacheConfiguration;
import org.owen.q.template_cache.config.TemplateViewCacheOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


@EnableWebMvc
@Configuration
@Import({ TemplateViewCacheConfiguration.class })
public class DemoMvcConfig {
    @Bean
    public CacheableFreeMarkerViewResolver cacheableFreeMarkerViewResolver() {
        CacheableFreeMarkerViewResolver resolver = new CacheableFreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer cacheableFreeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        return freeMarkerConfigurer;
    }

    @Bean
    public TemplateViewCacheOptions templateViewCacheConfiguration() {
        return TemplateViewCacheOptions.builder()
                                       .expireAfterWriteDuration(10L)
                                       .expireAfterWriteDurationUnit(TimeUnit.SECONDS)
                                       .build();
    }

    @Bean
    public TemplateViewCacheable viewCacheable(TemplateViewCacheOptions configuration) {
        return new CaffeineTemplateViewCache(configuration);
    }
}
