package com.kbldemo.service;

/**
 * Created by kbl on 2019/8/4.
 */
public interface RedisService {
    void remove(final String key);
    boolean exists(final String key);
    String get(final String key);
    boolean set(final String key, Object value);
    boolean set(final String key, Object value, Long expireTime);
    void removeAllRedis();
}
