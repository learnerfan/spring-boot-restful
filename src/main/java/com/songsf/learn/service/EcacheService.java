package com.songsf.learn.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by songsf on 2017/1/8.
 */
@Component
@Service
public class EcacheService implements CacheTest{

    @Cacheable("name")
    public String getByIsbn(String name) {
        return "hello"+name;
    }

    @Override
    @Cacheable("name")
    public String getMessage(String name) {
        return "hello"+name;
    }
}
