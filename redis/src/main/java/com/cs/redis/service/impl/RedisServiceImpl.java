package com.cs.redis.service.impl;

import com.cs.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void save(String key, Integer value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void save(String key, Boolean value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void save(String key, String field, Integer value) {
        redisTemplate.opsForHash().put(key,field,value);
    }
}
