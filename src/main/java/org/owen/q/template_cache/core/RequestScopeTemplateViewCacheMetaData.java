package org.owen.q.template_cache.core;

import lombok.extern.slf4j.Slf4j;

import org.owen.q.template_cache.model.TemplateViewCacheMetadata;

@Slf4j
public class RequestScopeTemplateViewCacheMetaData {
    private static final ThreadLocal<TemplateViewCacheMetadata> threadLocal = new ThreadLocal<>();

    public static TemplateViewCacheMetadata get() {
        TemplateViewCacheMetadata value = threadLocal.get();

        if (value == null) {
            threadLocal.set(new TemplateViewCacheMetadata());
            return threadLocal.get();
        }

        return value;
    }

    public static void setRenderedContent(String content) {
        threadLocal.get().setRenderedViewContent(content);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
