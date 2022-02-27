package org.owen.q.template_cache.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class TemplateCacheFinalizer implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestScopeTemplateViewCacheMetaData.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
