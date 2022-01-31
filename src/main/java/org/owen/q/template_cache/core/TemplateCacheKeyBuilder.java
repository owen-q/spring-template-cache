package org.owen.q.template_cache.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.owen.q.template_cache.cache.TemplateCacheKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TemplateCacheKeyBuilder {
    private static TemplateCacheKeyGenerator templateCacheKeyGenerator;

    @Autowired
    public void setTemplateCacheKeyGenerator(TemplateCacheKeyGenerator templateCacheKeyGenerator) {
        TemplateCacheKeyBuilder.templateCacheKeyGenerator = templateCacheKeyGenerator;
    }

    public static String get(String viewName, HttpServletRequest request, Map<String, Object> model) {
        return templateCacheKeyGenerator.generate(viewName, request, model);
    }
}
