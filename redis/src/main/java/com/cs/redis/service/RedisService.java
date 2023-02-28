package com.cs.redis.service;

public interface RedisService {
    void save(String key,Integer value);
    void save(String key,Boolean value);

    void save(String key, String field, Integer value);

}
