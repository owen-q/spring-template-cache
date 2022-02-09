package org.owen.q.template_cache.core;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.owen.q.template_cache.exception.TemplateCacheException;
import org.owen.q.template_cache.model.TemplateViewCacheMetadata;
import org.owen.q.template_cache.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Slf4j
public class CacheableFreeMarkerView extends FreeMarkerView {
    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TemplateViewCacheMetadata templateViewCacheMetadata = RequestScopeTemplateViewCacheMetaData.get();

        templateViewCacheMetadata.setCacheKey(TemplateCacheKeyBuilder.get(getBeanName(), request, model));

        String cachedRenderContents = CacheableViewRenderer.get(templateViewCacheMetadata.getCacheKey());

        if (StringUtils.isBlank(cachedRenderContents)) {
            if (log.isDebugEnabled()) {
                log.debug("[Render] render view. view={}", cachedRenderContents);
            }

            super.renderMergedTemplateModel(model, request, response);
            CacheableViewRenderer.set(templateViewCacheMetadata.getCacheKey(), templateViewCacheMetadata.getRenderedViewContent());
        } else {
            if (log.isDebugEnabled()) {
                log.debug("[Render] using cached view. view={}", cachedRenderContents);
            }

            response.getWriter().write(cachedRenderContents);
        }
    }

    @Override
    protected void processTemplate(Template template, SimpleHash model, HttpServletResponse response)
            throws IOException, TemplateException {
        Writer writer = new StringWriter();

        // render
        template.process(model, writer);

        String content = writer.toString();

        try {
            RequestScopeTemplateViewCacheMetaData.setRenderedContent(content);
        } catch(Exception e){
            throw new TemplateCacheException(e);
        }

        // send response
        response.getWriter().write(content);
    }
}
