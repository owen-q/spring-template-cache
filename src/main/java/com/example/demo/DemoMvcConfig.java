package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@Configuration
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
    public CacheableFreeMarkerConfigurer cacheableFreeMarkerConfigurer() {
        CacheableFreeMarkerConfigurer freeMarkerConfigurer = new CacheableFreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        return freeMarkerConfigurer;
    }

    @Bean
    public ViewCacheable viewCacheable() {
        return new CaffeineViewCache();
    }

    @Bean
    public CacheableViewRenderer cacheableViewRenderer() {
        return new CacheableViewRenderer();
    }
}
