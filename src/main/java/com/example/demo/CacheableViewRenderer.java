package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CacheableViewRenderer implements InitializingBean {
    private static ViewCacheable viewCacheable;

    @Autowired(required = false)
    public void setViewCacheable(ViewCacheable viewCacheable) {
        CacheableViewRenderer.viewCacheable = viewCacheable;
    }

    public static String get(String key) {
        return viewCacheable.get(key);
    }

    public static void set(String key, String content) {
        viewCacheable.set(key, content);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (viewCacheable == null) {
            viewCacheable = new CaffeineViewCache();
        }
    }
}
