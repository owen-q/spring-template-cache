package com.example.demo;

import org.springframework.beans.factory.FactoryBean;

public class CaffeineViewCacheFactoryBean implements FactoryBean<CaffeineViewCache> {
    @Override
    public CaffeineViewCache getObject() throws Exception {
        return new CaffeineViewCache();
    }

    @Override
    public Class<?> getObjectType() {
        return CaffeineViewCache.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
