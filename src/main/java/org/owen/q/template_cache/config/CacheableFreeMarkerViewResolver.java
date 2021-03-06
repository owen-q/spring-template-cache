package org.owen.q.template_cache.config;

import org.owen.q.template_cache.core.CacheableFreeMarkerView;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class CacheableFreeMarkerViewResolver extends AbstractTemplateViewResolver {
    /**
     * Sets the default {@link #setViewClass view class} to {@link #requiredViewClass}:
     * by default {@link FreeMarkerView}.
     */
    public CacheableFreeMarkerViewResolver() {
        setViewClass(requiredViewClass());
    }

    /**
     * A convenience constructor that allows for specifying {@link #setPrefix prefix}
     * and {@link #setSuffix suffix} as constructor arguments.
     * @param prefix the prefix that gets prepended to view names when building a URL
     * @param suffix the suffix that gets appended to view names when building a URL
     * @since 4.3
     */
    public CacheableFreeMarkerViewResolver(String prefix, String suffix) {
        this();
        setPrefix(prefix);
        setSuffix(suffix);
    }


    /**
     * Requires {@link CacheableFreeMarkerView}.
     */
    @Override
    protected Class<?> requiredViewClass() {
        return CacheableFreeMarkerView.class;
    }

    @Override
    protected AbstractUrlBasedView instantiateView() {
        return (getViewClass() == CacheableFreeMarkerView.class ? new CacheableFreeMarkerView() : super.instantiateView());
    }
}
