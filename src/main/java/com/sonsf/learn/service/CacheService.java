package com.sonsf.learn.service;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by songsf on 2017/1/6.
 */
public class CacheService {
    private CacheService(){}
    private static CacheService cacheService = null;
    private static Cache<String,Object> EXPTIME_1000_CACHE = null;
    private static Cache<String,Object> EXPTIME_1_CACHE = null;
    private static Cache<String,Object> NOT_EXPTIME_CACHE = null;
    public synchronized static CacheService getIns(){
        if (cacheService == null){
            cacheService = new CacheService();
            cacheService.init();
        }
        return cacheService;
    }
    public void init(){
        cacheService.EXPTIME_1000_CACHE = CacheBuilder.newBuilder().maximumSize(5000).expireAfterWrite(1000, TimeUnit.MINUTES).build();
        cacheService.EXPTIME_1_CACHE = CacheBuilder.newBuilder().maximumSize(5000).expireAfterWrite(1,TimeUnit.MINUTES).build();
        cacheService.NOT_EXPTIME_CACHE = CacheBuilder.newBuilder().maximumSize(5000).build();
    }
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(JSON.toJSONString(EXPTIME_1000_CACHE)).append("\n");
        sb.append(JSON.toJSONString(EXPTIME_1_CACHE)).append("\n");
        sb.append(JSON.toJSONString(NOT_EXPTIME_CACHE)).append("\n");
        return sb.toString();
    }
    //////////////////////////1分钟缓存机制///////////////////////////
    public Object getExpire1Cache(String key){
        return EXPTIME_1_CACHE.getIfPresent(key);
    }
    public void putExpire1Cache(String key,Object value){
        if (EXPTIME_1_CACHE.getIfPresent(key) == null){
            EXPTIME_1_CACHE.put(key,value);
        }
    }
    ///////////////////////1000分钟缓存机制//////////////////////////
    public Object getExpire1000Cache(String key){
        return EXPTIME_1000_CACHE.getIfPresent(key);
    }
    public void putExpire1000Cache(String key,Object value){
        if (EXPTIME_1000_CACHE.getIfPresent(key) == null){
            EXPTIME_1000_CACHE.put(key,value);
        }
    }


}
