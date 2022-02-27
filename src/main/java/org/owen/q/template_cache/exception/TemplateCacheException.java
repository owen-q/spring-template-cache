package org.owen.q.template_cache.exception;

public class TemplateCacheException extends RuntimeException {
    public TemplateCacheException() {
    }

    public TemplateCacheException(String message) {
        super(message);
    }

    public TemplateCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateCacheException(Throwable cause) {
        super(cause);
    }

    public TemplateCacheException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
