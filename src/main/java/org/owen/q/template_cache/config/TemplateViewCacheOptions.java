package org.owen.q.template_cache.config;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TemplateViewCacheOptions {
    private boolean useAsyncSet;
    private boolean useEvict;
    private Long expireAfterWriteDuration;
    private TimeUnit expireAfterWriteDurationUnit;

    private Long maximumSize;

    private Executor customExecutor;

    @Builder
    public TemplateViewCacheOptions(boolean useAsyncSet, boolean useEvict, Long expireAfterWriteDuration,
                                    TimeUnit expireAfterWriteDurationUnit, Long maximumSize,
                                    Executor customExecutor) {
        this.useAsyncSet = useAsyncSet;
        this.useEvict = useEvict;
        this.expireAfterWriteDuration = expireAfterWriteDuration;
        this.expireAfterWriteDurationUnit = expireAfterWriteDurationUnit;
        this.maximumSize = maximumSize;
        this.customExecutor = customExecutor;
    }
}
