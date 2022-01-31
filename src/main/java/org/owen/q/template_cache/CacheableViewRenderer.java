package org.owen.q.template_cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.owen.q.template_cache.cache.TemplateViewCacheable;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor
public class CacheableViewRenderer {
    private static TemplateViewCacheable templateViewCacheable;

    @Autowired
    public void setTemplateViewCacheable(TemplateViewCacheable templateViewCacheable) {
        CacheableViewRenderer.templateViewCacheable = templateViewCacheable;
    }

    public static String get(String key) {
        return templateViewCacheable.get(key);
    }

    public static void set(String key, String content) {
        templateViewCacheable.set(key, content);
    }
}
