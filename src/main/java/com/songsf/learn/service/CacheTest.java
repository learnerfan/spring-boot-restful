package com.songsf.learn.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by songsf on 2017/1/8.
 */
@CacheConfig(cacheNames = "name")
public interface CacheTest {
    @Cacheable
    String getMessage(String name);
}
