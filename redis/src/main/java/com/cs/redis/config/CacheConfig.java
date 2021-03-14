package com.cs.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        /**
         * 注册名为sampleCache的缓存
         */
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("sampleCache")));
        return cacheManager;
    }
}
