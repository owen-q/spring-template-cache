package org.owen.q.template_cache.cache;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface TemplateCacheKeyGenerator {
    String generate(String viewName, HttpServletRequest request, Map<String, Object> model);
}
