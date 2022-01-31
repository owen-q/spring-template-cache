package com.example.demo;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.view.AbstractTemplateView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Slf4j
public class CacheableFreeMarkerView extends FreeMarkerView {
    public static RequestScopeCacheMetaData requestScopeCacheMetaData = new RequestScopeCacheMetaData();

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        requestScopeCacheMetaData.remove();
        CacheMetadata cacheMetadata = requestScopeCacheMetaData.get();

        cacheMetadata.setKey(buildCacheKey(getBeanName(), model));
        String cachedRenderContents = CacheableViewRenderer.get(cacheMetadata.getKey());

        if (isBlank(cachedRenderContents)) {
            log.info("[Render] using freemarker rendering");
            super.renderMergedTemplateModel(model, request, response);
            CacheableViewRenderer.set(cacheMetadata.getKey(), cacheMetadata.getContent());
        } else {
            log.info("[Render] using cached view");
            response.getWriter().write(cachedRenderContents);
        }

        requestScopeCacheMetaData.remove();
    }

    private int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    private boolean isBlank(CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void processTemplate(Template template, SimpleHash model, HttpServletResponse response)
            throws IOException, TemplateException {
        Writer writer = new StringWriter();

        // render
        template.process(model, writer);

        String content = writer.toString();
        requestScopeCacheMetaData.get().setContent(content);

        // send response
        response.getWriter().write(content);
    }

    private String buildCacheKey(String beanName, Map<String, Object> model) {
        int mergedHashCode = 1;

        Object value;
        for (String modelName : model.keySet()) {
            if (!modelName.equalsIgnoreCase(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE)) {
                value = model.get(modelName);
                mergedHashCode = 31 * mergedHashCode + (value == null ? 0 : value.hashCode());
            }
        }

        return beanName + "." + mergedHashCode;
    }

}
