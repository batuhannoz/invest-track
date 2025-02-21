package com.finartz.investtrack.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected ConcurrentMapCache createConcurrentMapCache(String name) {
                System.out.println(name);
                if ("stockPriceCache".equals(name)) {
                    return new ConcurrentMapCache(name,
                            CacheBuilder.newBuilder()
                                    .expireAfterWrite(15, TimeUnit.MINUTES)
                                    .build()
                                    .asMap(),
                            false);
                } else {
                    return new ConcurrentMapCache(name,
                            CacheBuilder.newBuilder()
                                    .expireAfterWrite(24, TimeUnit.HOURS)
                                    .build()
                                    .asMap(),
                            false);
                }
            }
        };
    }

}
