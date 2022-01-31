package org.owen.q.template_cache.cache.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.owen.q.template_cache.cache.TemplateCacheKeyGenerator;
import org.springframework.web.servlet.view.AbstractTemplateView;

public class TemplateCacheDefaultKeyGenerator implements TemplateCacheKeyGenerator {

    @Override
    public String generate(String viewName, HttpServletRequest request, Map<String, Object> model) {
        int mergedHashCode = 1;

        Object value;
        for (String modelName : model.keySet()) {
            if (!modelName.equalsIgnoreCase(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE)) {
                value = model.get(modelName);
                mergedHashCode = 31 * mergedHashCode + (value == null ? 0 : value.hashCode());
            }
        }

        return viewName + "." + mergedHashCode;
    }
}
