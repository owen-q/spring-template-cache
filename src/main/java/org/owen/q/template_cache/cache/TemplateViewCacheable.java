package org.owen.q.template_cache.cache;

public interface TemplateViewCacheable {
    String get(String key);
    void set(String key, String content);
}
