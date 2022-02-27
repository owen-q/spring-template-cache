package org.owen.q.template_cache.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TemplateViewCacheMetadata {
    private String cacheKey;
    private String renderedViewContent;
}
