# spring-template-cache

Let's caching server-side-rending template rendering results.

template rendering is more complicated than you think. 

freemarker supports template cache, but it not a view.

If your system confront high TPS and try spring-template-cache

## Getting Started

### Create Configuration

### Add dependencies 
build.gradle
```groovy
dependencies {
    ...
    implementation 'io.github.owen-q:spring-template-cache:1.1'
    ...
}
```

```java

@Import({ TemplateViewCacheConfiguration.class })
@Configuration
public class TemplateViewCacheConfig {
    // required
    @Bean
    public CacheableFreeMarkerViewResolver cacheableFreeMarkerViewResolver() {
        CacheableFreeMarkerViewResolver resolver = new CacheableFreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setSuffix(".ftl");
        return resolver;
    }
    
    // required
    @Bean
    public TemplateViewCacheable templateViewCacheable() {
        return new CaffeineTemplateViewCache(TemplateViewCacheOptions.builder()
                                                                     .expireAfterWriteDuration(10L)
                                                                     .expireAfterWriteDurationUnit(TimeUnit.SECONDS)
                                                                     .maximumSize(1000L)
                                                                     .build());
    }
}

```

---   

### Import it!

Application.java
```java

@Import({ TemplateViewCacheConfig.class })
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

```

## Features
- caching freemarker rendering results
- other template engines will be supported
- using caffeine for default caching implementation
- caching implementation can be replaced by any caching libraries



