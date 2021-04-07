package com.kbldemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.kbldemo.service.RedisService;
import com.kbldemo.system.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by kbl on 2019/8/4.
 */
@Service
public class RedisServiceImpl implements RedisService {
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 删除对应的value
     *
     * @param key
     */
    @Override
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @Override
    public String get(final String key) {
        ValueOperations<String, String> valueopsJson = redisTemplate.opsForValue();
        return valueopsJson.get(key);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, String> valueopsJson = redisTemplate.opsForValue();
            valueopsJson.set(key, JSON.toJSONString(value));
            result = true;
        } catch (Exception ex) {
            Log4j.showLogInfo(ex.getMessage(),ex);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, String> valueopsJson = redisTemplate.opsForValue();
            valueopsJson.set(key, JSON.toJSONString(value));
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception ex) {
            Log4j.showLogInfo(ex.getMessage(),ex);
        }
        return result;
    }
    /**
     * 删除所有缓存
     *
     * @return
     */
    @Override
    public void removeAllRedis(){
        Set<String> keys = redisTemplate.keys("*");
    }

}
